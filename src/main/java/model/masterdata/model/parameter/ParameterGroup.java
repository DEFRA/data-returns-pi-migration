package model.masterdata.model.parameter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import model.masterdata.model.AbstractBaseEntity;
import model.masterdata.model.AbstractMasterDataEntity;
import model.masterdata.model.MasterDataEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the md_parameter_group database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_parameter_group")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_parameter_group_id_seq")
        }
)
@Getter
@Setter
public class ParameterGroup extends AbstractMasterDataEntity implements MasterDataEntity {
    @ManyToMany
    @JoinTable(name = "md_parameter_group_entries")
    @Setter(AccessLevel.NONE)
    private Set<Parameter> parameters = new HashSet<>();
}
