package model.masterdata.ewc;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractBaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * The persistent class for the EWC Activities
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_ewc_activity")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_ewc_activity_id_seq")}
)
@Getter
@Setter
public class EwcActivity extends AbstractEwcEntity {
    /**
     * The parent subchapter for this activity
     */
    @ManyToOne(optional = false)
    private EwcSubchapter ewcSubchapter;

    /**
     * Determines if the EWC activity is hazardous
     */
    private boolean hazardous;
}
