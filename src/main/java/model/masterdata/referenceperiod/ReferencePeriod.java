package model.masterdata.referenceperiod;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractAliasedEntity;
import model.masterdata.AbstractBaseEntity;
import model.masterdata.AliasedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * The persistent class for the reference_periods database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_reference_period")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_reference_period_id_seq")}
)
@Getter
@Setter
public class ReferencePeriod extends AbstractAliasedEntity<ReferencePeriodAlias> implements AliasedEntity<ReferencePeriodAlias> {
    @Basic
    @Column(name = "notes", length = 250)
    private String notes;
}
