package model.masterdata.nace;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractBaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the NACE groups
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_nace_group")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_nace_group_id_seq")}
)
@Getter
@Setter
public class NaceGroup extends AbstractNaceEntity {
    /**
     * The parent division for this group
     */
    @ManyToOne(optional = false)
    private NaceDivision naceDivision;

    /**
     * The classes within this group
     */
    @OneToMany(mappedBy = "naceGroup")
    @Setter(AccessLevel.NONE)
    private Set<NaceClass> naceClasses = new HashSet<>();
}
