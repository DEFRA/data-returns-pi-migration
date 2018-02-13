package model.pidec.authorizations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IsrAreaEntityRepository extends CrudRepository<IsrAreaEntity, Integer>, JpaRepository<IsrAreaEntity, Integer>
{

}
