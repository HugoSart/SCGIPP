package scgipp.data.hibernate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

import static scgipp.data.hibernate.BaseEntity.NORMAL;

/**
 * User: hugo_<br/>
 * Date: 23/08/2017<br/>
 * Time: 23:25<br/>
 */
@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> {

    public static final int NORMAL = 1;
    public static final int DELETED = 0;

    @Column(name = "entity_state")
    protected int state = NORMAL;

    @Id
    @GeneratedValue
    protected T id;

    public void setId(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void recover() {
        state = NORMAL;
    }

    @PrePersist
    private void persistEntity() {
        state = NORMAL;
    }

    @PreRemove
    private void deleteEntity() {
        state = DELETED;
    }

}
