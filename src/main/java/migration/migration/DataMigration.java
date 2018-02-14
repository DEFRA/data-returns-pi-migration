package migration.migration;

import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.masterdata.geography.Area;
import model.masterdata.geography.AreaRepository;
import model.masterdata.geography.Region;
import model.masterdata.geography.RegionRepository;
import model.pidec.authorizations.IsrAreaEntity;
import model.pidec.authorizations.IsrAreaEntityRepository;
import model.pidec.authorizations.IsrRegionEntity;
import model.pidec.authorizations.IsrRegionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("migration")
@Slf4j
@RequiredArgsConstructor
public class DataMigration {

    @Autowired IsrRegionEntityRepository isrRegionEntityRepository;
    @Autowired IsrAreaEntityRepository isrAreaEntityRepository;
    @Autowired AreaRepository areaRepository;
    @Autowired RegionRepository regionRepository;

    public void migrateArea() {
        try {
            log.info("Migrate areas and regions");

            Date timestamp = new Date();

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

        } catch (DataAccessException e) {
            log.error(e.toString());
        }
    }

}
