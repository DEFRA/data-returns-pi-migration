package model.masterdata.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Mapped superclass for master data entities
 *
 * @author Sam Gardner-Dell
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractMasterDataEntity extends AbstractBaseEntity implements MasterDataEntity, Serializable {
    @NaturalId
    @Column(name = "nomenclature", nullable = false, unique = true)
    @NotBlank
    private String nomenclature;

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AbstractMasterDataEntity that = (AbstractMasterDataEntity) o;
        return Objects.equals(getNomenclature(), that.getNomenclature());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getNomenclature());
    }
}
