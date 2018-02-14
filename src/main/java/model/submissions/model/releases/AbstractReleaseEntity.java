package model.submissions.model.releases;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractBaseEntity;
import model.submissions.model.submissions.Submission;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The mapped superclass for releases entities
 *
 * @author Sam Gardner-Dell
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractReleaseEntity extends AbstractBaseEntity implements Serializable {
    @ManyToOne(optional = false)
    private Submission submission;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReleaseMethod method;

    @Column
    private BigDecimal value;

    @Basic
    @Column(nullable = false)
    private boolean belowReportingThreshold;

    @Column
    private int unitId;

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (getId() == null) {
            return false;
        }
        final AbstractReleaseEntity that = (AbstractReleaseEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }

    public enum ReleaseMethod {
        Measurement,
        Calculation,
        Estimation;
    }
}

