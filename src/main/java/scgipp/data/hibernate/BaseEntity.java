<<<<<<< HEAD
package scgipp.data.hibernate;

import com.sun.org.apache.xerces.internal.impl.validation.EntityState;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.event.internal.AbstractSaveEventListener;

import javax.persistence.*;

/**
 * User: hugo_<br/>
 * Date: 23/08/2017<br/>
 * Time: 23:25<br/>
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    protected Integer id;

    private State state;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @PrePersist
    private void persistEntity() {
        state = State.NORMAL;
    }

    @PreRemove
    private void deleteEntity() {
        state = State.DELETED;
    }

    public enum State {
        DELETED, NORMAL
    }

}
=======
package scgipp.data.hibernate;

import com.sun.org.apache.xerces.internal.impl.validation.EntityState;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.event.internal.AbstractSaveEventListener;

import javax.persistence.*;

/**
 * User: hugo_<br/>
 * Date: 23/08/2017<br/>
 * Time: 23:25<br/>
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    protected Integer id;

    private State state;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @PrePersist
    private void persistEntity() {
        state = State.NORMAL;
    }

    @PreRemove
    private void deleteEntity() {
        state = State.DELETED;
    }

    public enum State {
        DELETED, NORMAL
    }

}
>>>>>>> [C]ObservableCustomer
