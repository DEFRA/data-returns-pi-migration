package migration.migration;

import lombok.extern.slf4j.Slf4j;
import model.masterdata.model.site.Site;
import model.masterdata.model.site.SiteRepository;
import model.pidec.authorizations.IsrAreaEntity;
import model.pidec.authorizations.IsrAreaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DataMigration {
    
    @Autowired
    IsrAreaEntityRepository isrAreaEntityRepository;

    @Autowired
    SiteRepository siteRepository;

    public void migrate() {
        try {
            log.info("Hello migration");
            List<IsrAreaEntity> areas = isrAreaEntityRepository.findAll();
            for(IsrAreaEntity a : areas) {
                log.info(a.toString());
            }

            List<Site> sites = siteRepository.findAll();
            for(Site s : sites) {
                log.info(s.toString());
            }

        } catch (DataAccessException e) {
            log.error(e.toString());
        }
    }
}
