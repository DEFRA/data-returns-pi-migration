package migration.migration;

import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.masterdata.eaid.*;
import model.masterdata.geography.Area;
import model.masterdata.geography.AreaRepository;
import model.masterdata.geography.Region;
import model.masterdata.geography.RegionRepository;
import model.masterdata.site.Site;
import model.masterdata.site.SiteRepository;
import model.pidec.authorizations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Profile("migration")
@Slf4j
@RequiredArgsConstructor
public class DataMigration {

    @Autowired IsrRegionEntityRepository isrRegionEntityRepository;
    @Autowired IsrAreaEntityRepository isrAreaEntityRepository;
    @Autowired IsrOperatorEntityRepository isrOperatorEntityRepository;
    @Autowired IsrAsrCodesEntityRepository isrAsrCodesEntityRepository;
    @Autowired IsrAsrFullCodesEntityRepository isrAsrFullCodesEntityRepository;
    @Autowired IsrSiteEntityRepository isrSiteEntityRepository;

    @Autowired AreaRepository areaRepository;
    @Autowired RegionRepository regionRepository;
    @Autowired OperatorRepository operatorRepository;
    @Autowired AsrCodeRepository asrCodeRepository;
    @Autowired SiteRepository siteRepository;
    @Autowired UniqueIdentifierRepository uniqueIdentifierRepository;
    @Autowired UniqueIdentifierGroupRepository uniqueIdentifierGroupRepository;

    private Date timestamp = new Date();

    private static String normalize(String s) {
        return StringUtils.trimWhitespace(s.toUpperCase()).replaceAll("\\s+", " ");
    }

    public void migrateArea() {
        areaRepository.deleteAll();
        regionRepository.deleteAll();

        areaRepository.flush();
        regionRepository.flush();

        // First create all the regions
        List<IsrRegionEntity> isrRegionEntities = isrRegionEntityRepository.findAll();

        List<Region> regions = isrRegionEntities.stream().map(isrRegionEntity -> {
            Region region = new Region();
            region.setCode(isrRegionEntity.getRegionshortname().trim());
            region.setNomenclature(isrRegionEntity.getRegionname());
            region.setCreated(timestamp);
            region.setLastModified(timestamp);
            return region;
        }).collect(Collectors.toList());

        log.info("Migrated regions: " + regions.size());
        regionRepository.save(regions);
        regionRepository.flush();

        // Now create the areas assigning the regions
        List<IsrAreaEntity> isrAreaEntities = isrAreaEntityRepository.findAll();

        List<Area> areas = isrAreaEntities.stream().map(isrAreaEntity -> {
            Area area = new Area();
            area.setNomenclature(isrAreaEntity.getAreaname());
            if (Strings.isNullOrEmpty(isrAreaEntity.getAreashortname()) == false) {
                area.setCode(isrAreaEntity.getAreashortname().trim());
            }
            area.setCreated(timestamp);
            area.setLastModified(timestamp);
            // Get the appropriate region
            IsrRegionEntity isrRegionEntity = isrAreaEntity.getIsrRegionByRegionid();
            // Lookup the master data region
            Region region = regionRepository.getByNomenclature(isrRegionEntity.getRegionname());
            area.setRegion(region);
            return area;
        }).collect(Collectors.toList());

        log.info("Migrated areas: " + areas.size());
        areaRepository.save(areas);
        areaRepository.flush();
    }

    public void migrateOperator() {
        operatorRepository.deleteAll();
        List<IsrOperatorEntity> isrOperatorEntities = isrOperatorEntityRepository.findAll();

        List<Operator> operators = isrOperatorEntities.stream()
                .map(isrOperatorEntity -> normalize(isrOperatorEntity.getOperatorname()))
                .distinct()
                .map(s -> {
                    Operator operator = new Operator();
                    operator.setNomenclature(s);
                    operator.setCreated(timestamp);
                    operator.setLastModified(timestamp);
                    return operator;
                }).collect(Collectors.toList());

        operatorRepository.save(operators);
        operatorRepository.flush();

        log.info("Migrated operators: " + operators.size());
    }

    public void migrateAsr() {
        final List<IsrAsrFullCodesEntity> asrFullCodesEntities = isrAsrFullCodesEntityRepository.findAll();

        List<AsrCode> asrCodes = asrFullCodesEntities.stream().map(isrAsrFullCodesEntity -> {
            AsrCode asrCode = new AsrCode();
            asrCode.setNomenclature(isrAsrFullCodesEntity.getAsrFullCode());
            asrCode.setAsrFullCodeDescription(isrAsrFullCodesEntity.getAsrFullCodeDescription());
            IsrAsrCodesEntity isrAsrCodesByAsrCode = isrAsrFullCodesEntity.getIsrAsrCodesByAsrCode();
            asrCode.setAsrCode(isrAsrCodesByAsrCode.getAsrCode());
            asrCode.setAsrCodeDescription(isrAsrCodesByAsrCode.getAsrCodeDescription());
            asrCode.setCreated(timestamp);
            asrCode.setLastModified(timestamp);
            return asrCode;
        }).collect(Collectors.toList());

        asrCodeRepository.deleteAll();
        asrCodeRepository.save(asrCodes);
        asrCodeRepository.flush();

        log.info("Migrated ASR codes: " + asrCodes.size());
    }

    public void migrateSite() {
        List<Site> currentSites = siteRepository.findAll();

        Set<String> currentSitesSet = currentSites.stream()
                .map(Site::getNomenclature)
                .map(s -> normalize(s))
                .collect(Collectors.toSet());

        List<IsrSiteEntity> isrSiteEntities = isrSiteEntityRepository.findAll();

        List<Site> sites = isrSiteEntities.stream()
                .map(IsrSiteEntity::getSiteaddress)
                .filter(s -> !Strings.isNullOrEmpty(s))
                .map(s -> normalize(s))
                .filter(s -> !currentSitesSet.contains(s))
                .distinct()
                .map(s -> {
                    Site site = new Site();
                    site.setNomenclature(normalize(s));
                    site.setCreated(timestamp);
                    site.setLastModified(timestamp);
                    return site;
                }).collect(Collectors.toList());

        siteRepository.save(sites);
        siteRepository.flush();

        log.info("Migrated sites: " + sites.size());
    }


    public void migratePermits() {
    }
}
