package model.masterdata.disposalsandrecoveries;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractMasterDataEntity;
import model.masterdata.AbstractBaseEntity;
import model.masterdata.MasterDataEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * The persistent class for the md_disposal_codes database table.
 *
 * @author Druid Wood Limited
 */
@Entity(name = "md_disposal_codes")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_disposal_codes_id_seq") }
)
@Getter @Setter
public class DisposalCode extends AbstractMasterDataEntity implements MasterDataEntity {
    @Basic
    @Column(name = "description")
    private String description;

}
