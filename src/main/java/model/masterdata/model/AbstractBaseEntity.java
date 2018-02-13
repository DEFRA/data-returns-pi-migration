package model.masterdata.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Base class for all entities represented by this API
 *
 * @author Sam Gardner-Dell
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractBaseEntity {
    /**
     * ID Generator Name
     */
    public static final String DEFINITIONS_ID_GENERATOR = "definitions_idgen";
    /**
     * ID Generator Strategy
     */
    public static final String DEFINITIONS_ID_SEQUENCE_STRATEGY = "org.hibernate.id.enhanced.SequenceStyleGenerator";

    /**
     * Primary key of the entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = DEFINITIONS_ID_GENERATOR)
    @Column(name = "id")
    private Long id;

    /**
     * Creation date of the entity
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date created;

    /**
     * Last modified date of the entity
     */
    @LastModifiedDate
    @Column(nullable = false)
    private Date lastModified;

    /**
     * Version of the entity (ETag support)
     */
    @Version
    @Column(nullable = false)
    private short version;

    /*
     * Force subclasses to implement equals(Object)
     */
    @Override
    public abstract boolean equals(Object o);

    /*
     * Force subclasses to implement hashCode()
     */
    @Override
    public abstract int hashCode();
}
