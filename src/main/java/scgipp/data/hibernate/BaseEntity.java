package scgipp.data.hibernate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static scgipp.data.hibernate.BaseEntity.DELETED;
import static scgipp.data.hibernate.BaseEntity.NORMAL;

/**
 * User: hugo_<br/>
 * Date: 23/08/2017<br/>
 * Time: 23:25<br/>
 */
@MappedSuperclass
public abstract class BaseEntity {

    public static final int NORMAL = 1;
    public static final int DELETED = 0;

    protected int state = NORMAL;

    @Id
    @GeneratedValue
    protected Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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
