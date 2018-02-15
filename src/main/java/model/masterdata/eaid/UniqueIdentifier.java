package model.masterdata.eaid;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.AbstractAliasedEntity;
import model.masterdata.AbstractBaseEntity;
import model.masterdata.AliasedEntity;
import model.masterdata.geography.Area;
import model.masterdata.site.Site;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import javax.persistence.*;

/**
 * The persistent class for the unique_identifiers database table.
 *
 * @author Graham Willis
 */
@Entity(name = "md_unique_identifier")
@GenericGenerator(name = AbstractBaseEntity.DEFINITIONS_ID_GENERATOR,
        strategy = AbstractBaseEntity.DEFINITIONS_ID_SEQUENCE_STRATEGY,
        parameters = {
                @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "md_unique_identifier_id_seq")}
)
@Getter
@Setter
public class UniqueIdentifier extends AbstractAliasedEntity<UniqueIdentifierAlias> implements AliasedEntity<UniqueIdentifierAlias> {

    public enum Type {
        IPC,
        RAS,
        IPPC,
        WML,
        WIA,
        EPRTR
    }

    @ManyToOne(optional = false)
    private Site site;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    private Operator operator;

    @ManyToOne
    private Area area;

    @ManyToOne
    private AsrCode asrCode;

}
