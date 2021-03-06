package model.masterdata.referenceperiod;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractMasterDataEntity;
import model.masterdata.MasterDataEntity;
import model.masterdata.AbstractBaseEntity;
import model.masterdata.AliasingEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The persistent class for reference_period aliases.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_reference_period_alias")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = SequenceStyleGenerator.SEQUENCE_PARAM,
                        value = "md_reference_period_alias_id_seq"
                )
        }
)
@Getter
@Setter
public class ReferencePeriodAlias extends AbstractMasterDataEntity implements MasterDataEntity, AliasingEntity<ReferencePeriod> {
    @ManyToOne
    @JoinColumn(name = "preferred", nullable = false)
    private ReferencePeriod preferred;
}
