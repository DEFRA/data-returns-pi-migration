package model.masterdata.model.unit;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.model.AbstractBaseEntity;
import model.masterdata.model.AbstractMasterDataEntity;
import model.masterdata.model.AliasingEntity;
import model.masterdata.model.MasterDataEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The persistent class for unit aliases.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_unit_alias")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = SequenceStyleGenerator.SEQUENCE_PARAM,
                        value = "md_unit_alias_id_seq"
                )
        }
)
@Getter
@Setter
public class UnitAlias extends AbstractMasterDataEntity implements MasterDataEntity, AliasingEntity<Unit> {
    @ManyToOne
    @JoinColumn(name = "preferred", nullable = false)
    private Unit preferred;
}
