package model.masterdata.model.ewc;

import lombok.Getter;
import lombok.Setter;
import model.masterdata.model.AbstractMasterDataEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Mapped superclass for all EWC based entities
 *
 * @author Sam Gardner-Dell
 */
@MappedSuperclass
@Getter
@Setter
public class AbstractEwcEntity extends AbstractMasterDataEntity {
    @Column(name = "code", length = 2, nullable = false)
    @NotBlank
    private String code;

    @Basic
    @Column(name = "description", length = 500, nullable = false)
    private String description;
}
