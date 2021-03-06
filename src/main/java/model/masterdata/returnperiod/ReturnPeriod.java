package model.masterdata.returnperiod;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractMasterDataEntity;
import model.masterdata.MasterDataEntity;
import model.masterdata.AbstractBaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * The persistent class for the return_periods database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_return_period")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_return_period_id_seq")}
)
@Getter
@Setter
public class ReturnPeriod extends AbstractMasterDataEntity implements MasterDataEntity {
    @Basic
    @Column(name = "definition", length = 600)
    private String definition;

    @Basic
    @Column(name = "example", length = 20)
    private String example;
}
