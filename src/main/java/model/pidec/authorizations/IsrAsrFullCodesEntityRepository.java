package model.pidec.authorizations;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface IsrAsrFullCodesEntityRepository
        extends JpaRepository<IsrAsrFullCodesEntity, Integer>, JpaSpecificationExecutor<IsrAsrFullCodesEntity>, QueryDslPredicateExecutor<IsrAsrFullCodesEntity> {
}
