package model.masterdata.parameter;

import model.masterdata.MasterDataRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring REST repository for {@link ParameterAlias} entities
 *
 * @author Sam Gardner-Dell
 */
@Repository
public interface ParameterAliasRepository extends MasterDataRepository<ParameterAlias> {
}
