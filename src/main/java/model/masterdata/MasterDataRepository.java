package model.masterdata;

import model.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Common repository definition for master data entities
 *
 * @param <E> the type of the entity
 * @author Sam Gardner-Dell
 */
@NoRepositoryBean
public interface MasterDataRepository<E extends MasterDataEntity> extends BaseRepository<E, Long> {
    /**
     * Retrieve a master data entity by its code
     *
     * @param nomenclature the primary term/name for the entity to retrieve
     * @return The entity E or null
     */
    @SuppressWarnings("unused")
    E getByNomenclature(@Param("nomenclature") String nomenclature);

    // FIXME Security needs further work..
    @Override
    <S extends E> S save(S entity);

    @Override
    <S extends E> List<S> save(Iterable<S> entities);

    @Override
    void deleteInBatch(Iterable<E> entities);

    @Override
    void deleteAllInBatch();

    @Override
    void delete(Long aLong);

    @Override
    void delete(E entity);

    @Override
    void delete(Iterable<? extends E> entities);

    @Override
    void deleteAll();
}
