package model.masterdata.eaid;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractMasterDataEntity;
import model.masterdata.AbstractBaseEntity;
import model.masterdata.AliasingEntity;
import model.masterdata.MasterDataEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The persistent class for the unique_identifiers database table.
 *
 * @author Graham Willis
 */
@Entity(name = "md_unique_identifier_alias")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = SequenceStyleGenerator.SEQUENCE_PARAM,
                        value = "md_unique_identifier_alias_id_seq"
                )
        }
)
@Getter
@Setter
public class UniqueIdentifierAlias extends AbstractMasterDataEntity implements MasterDataEntity, AliasingEntity<UniqueIdentifier> {
    @ManyToOne
    @JoinColumn(name = "preferred", nullable = false)
    private UniqueIdentifier preferred;
}
