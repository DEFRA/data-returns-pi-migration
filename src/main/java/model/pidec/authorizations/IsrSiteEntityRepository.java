package model.pidec.authorizations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface IsrSiteEntityRepository extends JpaRepository<IsrSiteEntity, Integer>,
        JpaSpecificationExecutor<IsrSiteEntity>, QueryDslPredicateExecutor<IsrSiteEntity> {
}
