package model.submissions.model.transfers;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractBaseEntity;
import model.masterdata.Address;
import model.submissions.model.submissions.Submission;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * PI Overseas waste transfers model
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "pi_transfer_overseas_waste")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "pi_transfer_overseas_waste_id_seq")}
)
@Getter
@Setter
public class OverseasWasteTransfer extends AbstractBaseEntity {
    @ManyToOne(optional = false)
    private Submission submission;

    @Basic
    private String responsibleCompanyName;

    @Embedded
    private Address responsibleCompanyAddress;

    @Embedded
    private Address destinationAddress;

    @Column(nullable = false)
    private int substanceId;

    @Column(nullable = false)
    private BigDecimal tonnage;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OverseasWasteTransferOperation operation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OverseasWasteTransferMethod method;

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
        final OverseasWasteTransfer that = (OverseasWasteTransfer) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }

    public enum OverseasWasteTransferOperation {
        Disposal, Recovery;
    }

    public enum OverseasWasteTransferMethod {
        Weighing, Calculation, Estimation;
    }
}
