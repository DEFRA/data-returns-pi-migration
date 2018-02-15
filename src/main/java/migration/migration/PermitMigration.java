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
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Profile("migration")
@Slf4j
@RequiredArgsConstructor
public class PermitMigration {

    private final IsrRegionEntityRepository isrRegionEntityRepository;
    private final IsrAreaEntityRepository isrAreaEntityRepository;
    private final IsrOperatorEntityRepository isrOperatorEntityRepository;
    private final IsrAsrCodesEntityRepository isrAsrCodesEntityRepository;
    private final IsrAsrFullCodesEntityRepository isrAsrFullCodesEntityRepository;
    private final IsrSiteEntityRepository isrSiteEntityRepository;
    private final IsrAuthorisationEntityRepository isrAuthorisationEntityRepository;
    private final IsrAuthorisationTypeEntityRepository isrAuthorisationTypeEntityRepository;

    private final AreaRepository areaRepository;
    private final RegionRepository regionRepository;
    private final OperatorRepository operatorRepository;
    private final AsrCodeRepository asrCodeRepository;
    private final SiteRepository siteRepository;
    private final UniqueIdentifierRepository uniqueIdentifierRepository;
    private final UniqueIdentifierAliasRepository uniqueIdentifierAliasRepository;
    private final UniqueIdentifierGroupRepository uniqueIdentifierGroupRepository;

    private Date timestamp = new Date();

    private static String normalize(String s) {
        return StringUtils.trimWhitespace(s.toUpperCase()).replaceAll("\\s+", " ");
    }

    public void migrate() {
        migrateArea();
        migrateOperator();
        migrateAsr();
        migrateSite();
        migratePermits();
    }

    private void migrateArea() {
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

    private void migrateOperator() {
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

    private void migrateAsr() {
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

    private void migrateSite() {
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


    private void migratePermits() {
        List<IsrAuthorisationEntity> authorisations = isrAuthorisationEntityRepository.findAll();

        List<IsrAuthorisationEntity> newPermits = authorisations.stream()
                .filter(isrAuthorisationEntity ->
                        uniqueIdentifierRepository.getByNomenclature(isrAuthorisationEntity.getAuthorisationid()) == null)
                .filter(isrAuthorisationEntity ->
                        uniqueIdentifierAliasRepository.getByNomenclature(isrAuthorisationEntity.getAuthorisationid()) == null)
                .collect(Collectors.toList());

        List<IsrAuthorisationEntity> existsAsPrimary = authorisations.stream()
                .filter(isrAuthorisationEntity ->
                        uniqueIdentifierRepository.getByNomenclature(isrAuthorisationEntity.getAuthorisationid()) != null)
                .collect(Collectors.toList());

        List<IsrAuthorisationEntity> existsAsAlias = authorisations.stream()
                .filter(isrAuthorisationEntity ->
                        uniqueIdentifierAliasRepository.getByNomenclature(isrAuthorisationEntity.getAuthorisationid()) != null)
                .collect(Collectors.toList());

        log.info("PI-DEC permits which are new permits: " + newPermits.size());
        log.info("PI-DEC permits with existing base permits: " + existsAsPrimary.size());
        log.info("PI-DEC permits with existing alias permits: " + existsAsAlias.size());

        // Process the new permits
        List<UniqueIdentifier> uniqueIdentifiers = newPermits
                .stream()
                .filter(isrAuthorisationEntity -> isrAuthorisationEntity.getIsrSiteBySiteid() != null &&
                        !Strings.isNullOrEmpty(isrAuthorisationEntity.getIsrSiteBySiteid().getSiteaddress()))
                .map(s -> {
                    UniqueIdentifier uniqueIdentifier = new UniqueIdentifier();
                    uniqueIdentifier.setNomenclature(s.getAuthorisationid());

                    if (s.getIsrAsrFullCodesByAsrFullCode() != null) {
                        AsrCode asrCode = asrCodeRepository.getByNomenclature(s.getIsrAsrFullCodesByAsrFullCode().getAsrFullCode());
                        uniqueIdentifier.setAsrCode(asrCode);
                    }

                    if (s.getIsrOperatorByOperatorid() != null) {
                        Operator operator = operatorRepository.getByNomenclature(normalize(s.getIsrOperatorByOperatorid().getOperatorname()));
                        uniqueIdentifier.setOperator(operator);
                    }

                    if (s.getIsrAreaByAgencyareaid() != null) {
                        Area area = areaRepository.getByNomenclature(s.getIsrAreaByAgencyareaid().getAreaname());
                        uniqueIdentifier.setArea(area);
                    }

                    if (s.getIsrAuthorisationtypeByAuthorisationtypeid() != null) {
                        String type = s.getIsrAuthorisationtypeByAuthorisationtypeid().getAuthorisationtypename();
                        uniqueIdentifier.setType(UniqueIdentifier.Type.valueOf(type));
                    }

                    Site site = siteRepository.getByNomenclature(normalize(s.getIsrSiteBySiteid().getSiteaddress()));
                    uniqueIdentifier.setSite(site);
                    uniqueIdentifier.setCreated(timestamp);
                    uniqueIdentifier.setLastModified(timestamp);

                    return uniqueIdentifier;
        }).collect(Collectors.toList());

        uniqueIdentifierRepository.save(uniqueIdentifiers);
        uniqueIdentifierRepository.flush();

        log.info("Migrated permits: " + uniqueIdentifiers.size());
    }

}
