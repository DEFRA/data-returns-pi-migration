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
 * The persistent class for the NACE divisions
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_nace_division")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_nace_division_id_seq")}
)
@Getter
@Setter
public class NaceDivision extends AbstractNaceEntity {
    /**
     * The parent section for this division
     */
    @ManyToOne(optional = false)
    private NaceSection naceSection;

    /**
     * The groups within this division
     */
    @OneToMany(mappedBy = "naceDivision")
    @Setter(AccessLevel.NONE)
    private Set<NaceGroup> naceGroups = new HashSet<>();
}
