package model.masterdata.returntype;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractMasterDataEntity;
import model.masterdata.MasterDataEntity;
import model.masterdata.AbstractBaseEntity;
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
