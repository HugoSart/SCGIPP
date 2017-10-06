package scgipp.data.hibernate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * User: hugo_<br/>
 * Date: 23/08/2017<br/>
 * Time: 23:25<br/>
 */
@MappedSuperclass
public abstract class Entity {

    @Id
    @GeneratedValue
    protected Integer id;

    public static enum Tipo{
        FISICA,
        JURIDICA
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
