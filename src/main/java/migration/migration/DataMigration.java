package migration.migration;

import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.masterdata.eaid.Operator;
import model.masterdata.eaid.OperatorRepository;
import model.masterdata.geography.Area;
import model.masterdata.geography.AreaRepository;
import model.masterdata.geography.Region;
import model.masterdata.geography.RegionRepository;
import model.pidec.authorizations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
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

    @Autowired AreaRepository areaRepository;
    @Autowired RegionRepository regionRepository;
    @Autowired OperatorRepository operatorRepository;

    private Date timestamp = new Date();
    private Pattern multiSpace = Pattern.compile("\\s+");

    public void migrateArea() {
        log.info("Migrate areas and regions");

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

        areaRepository.save(areas);
        areaRepository.flush();
    }

    public void migrateOperator() {
        log.info("Migrate operators");

        operatorRepository.deleteAll();
        List<IsrOperatorEntity> isrOperatorEntities = isrOperatorEntityRepository.findAll();

        List<Operator> operators = isrOperatorEntities.stream()
                .map(isrOperatorEntity -> StringUtils.trimWhitespace(isrOperatorEntity.getOperatorname()
                        .toUpperCase()).replaceAll("\\s+", " "))
                .distinct()
                .map(s -> {
                    Operator operator = new Operator();
                    operator.setNomenclature(s);
                    operator.setCreated(timestamp);
                    operator.setLastModified(timestamp);
                    return operator;
                }).collect(Collectors.toList());

        operatorRepository.save(operators);
    }
}
