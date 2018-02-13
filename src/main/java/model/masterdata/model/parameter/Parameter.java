package model.masterdata.model.parameter;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.model.AbstractAliasedEntity;
import model.masterdata.model.AbstractBaseEntity;
import model.masterdata.model.AliasedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * The persistent class for the parameters database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_parameter")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_parameter_id_seq")
        }
)
@Getter
@Setter
public class Parameter extends AbstractAliasedEntity<ParameterAlias> implements AliasedEntity<ParameterAlias> {
    @Basic
    @Column(name = "cas", length = 50)
    private String cas;

    @Basic
    @Column(name = "type", length = 100)
    private String type;
}
