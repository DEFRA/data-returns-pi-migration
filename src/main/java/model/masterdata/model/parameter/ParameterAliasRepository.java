package model.masterdata.model.parameter;

import model.masterdata.model.MasterDataRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring REST repository for {@link ParameterAlias} entities
 *
 * @author Sam Gardner-Dell
 */
@Repository
public interface ParameterAliasRepository extends MasterDataRepository<ParameterAlias> {
}
