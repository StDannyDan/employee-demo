package demo.model.core;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Extend this class by all entities
 *
 * @author Erofeev Danil
 */
@MappedSuperclass
public abstract class CoreEntity implements Serializable {
    private static final long serialVersionUID = 7711260969246401874L;
    /**
     * Override this by primary key
     * with javax.persistence.AttributeOverride annotation
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long uid) {
        this.id = uid;
    }
}
