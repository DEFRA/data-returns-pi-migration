package model.pidec.authorizations;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface IsrAreaEntityRepository extends JpaRepository<IsrAreaEntity, Integer>, JpaSpecificationExecutor<IsrAreaEntity>, QueryDslPredicateExecutor<IsrAreaEntity> {
}
