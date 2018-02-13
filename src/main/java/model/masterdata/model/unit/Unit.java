package model.masterdata.model.unit;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.model.AbstractAliasedEntity;
import model.masterdata.model.AbstractBaseEntity;
import model.masterdata.model.AliasedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;

/**
 * The persistent class for the units database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_unit")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_unit_id_seq")}
)
@Getter
@Setter
public class Unit extends AbstractAliasedEntity<UnitAlias> implements AliasedEntity<UnitAlias> {
    @Basic
    @Column(name = "long_name", length = 50)
    private String longName;

    @Basic
    @Column(name = "unicode", length = 50)
    private String unicode;

    @Basic
    @Column(name = "description", length = 200)
    private String description;

    @ManyToOne(optional = false)
    private UnitType type;
}
