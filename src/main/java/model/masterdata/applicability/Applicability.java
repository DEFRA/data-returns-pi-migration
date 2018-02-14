package model.masterdata.applicability;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractMasterDataEntity;
import model.masterdata.eaid.UniqueIdentifierGroup;
import model.masterdata.AbstractBaseEntity;
import model.masterdata.MasterDataEntity;
import model.masterdata.parameter.ParameterGroup;
import model.masterdata.returntype.ReturnTypeGroup;
import model.masterdata.unit.UnitGroup;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the md_applicability database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_applicability")
@Cacheable
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_applicability_id_seq")
        }
)
@Getter
@Setter
public class Applicability extends AbstractMasterDataEntity implements MasterDataEntity {
    @ManyToMany
    @Setter(AccessLevel.NONE)
    private Set<UniqueIdentifierGroup> uniqueIdentifierGroups = new HashSet<>();

    @ManyToMany
    @Setter(AccessLevel.NONE)
    private Set<ReturnTypeGroup> returnTypeGroups = new HashSet<>();

    @ManyToMany
    @Setter(AccessLevel.NONE)
    private Set<ParameterGroup> parameterGroups = new HashSet<>();

    @ManyToMany
    @Setter(AccessLevel.NONE)
    private Set<UnitGroup> unitGroups = new HashSet<>();
}
