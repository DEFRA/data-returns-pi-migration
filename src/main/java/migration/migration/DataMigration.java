package migration.migration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.masterdata.geography.AreaRepository;
import model.masterdata.geography.Region;
import model.masterdata.geography.RegionRepository;
import model.pidec.authorizations.IsrAreaEntityRepository;
import model.pidec.authorizations.IsrRegionEntity;
import model.pidec.authorizations.IsrRegionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //@Transactional
    public void migrateArea() {
        try {
            log.info("Migrate areas and regions");

            Date timestamp = new Date();

            // First create all the regions
            List<IsrRegionEntity> isrRegionEntities = isrRegionEntityRepository.findAll();

            log.info(isrRegionEntities.toString());

            List<Region> regions = isrRegionEntities.stream().map(isrRegionEntity -> {
                Region region = new Region();
                region.setNomenclature(isrRegionEntity.getRegionshortname());
                region.setDescription(isrRegionEntity.getRegionname());
                region.setCreated(timestamp);
                region.setLastModified(timestamp);
                return region;
            }).collect(Collectors.toList());

            areaRepository.deleteAll();
            regionRepository.deleteAll();

            regions.stream().forEach(region -> regionRepository.save(region));
            regionRepository.flush();

        } catch (DataAccessException e) {
            log.error(e.toString());
        }
    }

}
