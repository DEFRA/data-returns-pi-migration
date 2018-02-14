package model.masterdata.geography;

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
 * The persistent class for the unique_identifiers database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_region")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_region_id_seq")}
)
@Getter
@Setter
public class Region extends AbstractMasterDataEntity implements MasterDataEntity {
    @Basic
    @Column(name = "code", length = 2, nullable = false)
    private String code;
}
