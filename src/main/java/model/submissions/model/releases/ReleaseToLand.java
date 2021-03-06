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
 * PI Releases to land
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "pi_release_to_land")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uniq_release_to_land_substance", columnNames = {"submission_id", "substanceId"})
})
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "pi_release_to_land_id_seq")}
)
@Getter
@Setter
public class ReleaseToLand extends AbstractReleaseEntity {
    @Basic
    protected int substanceId;
}
