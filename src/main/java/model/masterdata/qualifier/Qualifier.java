package model.masterdata.qualifier;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractBaseEntity;
import model.masterdata.AbstractMasterDataEntity;
import model.masterdata.MasterDataEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * The persistent class for the qualifiers database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_qualifier")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_qualifier_id_seq") }
)
@Getter @Setter
public class Qualifier extends AbstractMasterDataEntity implements MasterDataEntity {
    @Basic
    @Column(name = "notes", length = 100)
    private String notes;

    @Basic
    @Column(name = "type", length = 50)
    private String type;

    @Basic
    @Column(name = "singleormultiple", length = 20)
    private String singleOrMultiple;
}
