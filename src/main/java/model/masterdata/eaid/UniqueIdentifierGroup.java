package model.masterdata.eaid;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractMasterDataEntity;
import model.masterdata.AbstractBaseEntity;
import model.masterdata.MasterDataEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the md_unique_identifier_group database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_unique_identifier_group")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_unique_identifier_group_id_seq")}
)
@Getter
@Setter
public class UniqueIdentifierGroup extends AbstractMasterDataEntity implements MasterDataEntity {
    @ManyToMany
    @JoinTable(name = "md_unique_identifier_group_entries")
    private Set<UniqueIdentifier> uniqueIdentifiers = new HashSet<>();
}
