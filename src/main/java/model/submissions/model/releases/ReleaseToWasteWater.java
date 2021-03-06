package model.submissions.model.releases;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractBaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * PI Releases to controlled water
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "pi_release_to_waste_water")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uniq_release_to_waste_water_substance", columnNames = {"submission_id", "substanceId"})
})
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "pi_release_to_waste_water_id_seq")}
)
@Getter
@Setter
public class ReleaseToWasteWater extends AbstractReleaseEntity {
    @Basic
    private int substanceId;
}
