package model.masterdata.regime;


import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractBaseEntity;
import model.masterdata.AbstractMasterDataEntity;
import model.masterdata.MasterDataEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Entity;

/**
 * The persistent class for the md_regime database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_regime")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_regime_id_seq")
        }
)
@Getter
@Setter
public class Regime extends AbstractMasterDataEntity implements MasterDataEntity {
}
