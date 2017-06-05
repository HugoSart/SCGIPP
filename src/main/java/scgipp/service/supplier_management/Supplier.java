package scgipp.service.supplier_management;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Supplier {

    @Id @GeneratedValue
    private Integer id;

    @ElementCollection
    private List<String> phone;

    @ElementCollection
    private List<String> address;

}
