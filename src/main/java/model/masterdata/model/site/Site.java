package model.masterdata.model.site;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.masterdata.model.AbstractBaseEntity;
import model.masterdata.model.AbstractMasterDataEntity;
import model.masterdata.model.MasterDataEntity;
import model.masterdata.model.eaid.UniqueIdentifier;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the unique_identifiers database table.
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "md_site")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_site_id_seq")}
)
@Getter
@Setter
@ToString
public class Site extends AbstractMasterDataEntity implements MasterDataEntity {
    @OneToMany(mappedBy = "site")
    @Setter(AccessLevel.NONE)
    private Set<UniqueIdentifier> uniqueIdentifiers = new HashSet<>();
}
