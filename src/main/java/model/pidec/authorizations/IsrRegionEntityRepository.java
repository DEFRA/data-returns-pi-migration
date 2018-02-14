package model.pidec.authorizations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface IsrRegionEntityRepository extends JpaRepository<IsrRegionEntity, Integer>, JpaSpecificationExecutor<IsrRegionEntity>, QueryDslPredicateExecutor<IsrRegionEntity> {
}
