package model.masterdata.eaid;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.Context;
import model.masterdata.AbstractAliasedEntity;
import model.masterdata.AbstractBaseEntity;
import model.masterdata.AliasedEntity;
import model.masterdata.geography.Area;
import model.masterdata.site.Site;
import model.masterdata.regime.Regime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

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

    @ManyToOne(optional = false)
    private Site site;

    @ManyToOne
    private Operator operator;

    @ManyToOne
    private Area area;

    @ManyToOne
    private AsrCode asrCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="md_regime_unique_identifiers",
            joinColumns={ @JoinColumn(name="unique_identifier_id", referencedColumnName="id") },
            inverseJoinColumns={ @JoinColumn(name="regime_id", referencedColumnName="id" ) })
    @MapKeyColumn(name = "context")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Context, Regime> regime = new HashMap<>();

}
