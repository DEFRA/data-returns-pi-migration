package model.masterdata.geography;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractBaseEntity;
import model.masterdata.AbstractMasterDataEntity;
import model.masterdata.MasterDataEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;

/**
 * The persistent class for the unique_identifiers database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_area")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_area_id_seq")}
)
@Getter
@Setter
public class Area extends AbstractMasterDataEntity implements MasterDataEntity {


    @ManyToOne
    private Region region;

    @Basic
    @Column(name = "code", length = 4)
    private String code;
}
