package model.masterdata.model.returntype;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.model.AbstractBaseEntity;
import model.masterdata.model.AbstractMasterDataEntity;
import model.masterdata.model.MasterDataEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Entity;

/**
 * The persistent class for the return_types database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_return_type")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_return_type_id_seq")}
)
@Getter
@Setter
public class ReturnType extends AbstractMasterDataEntity implements MasterDataEntity {
}
