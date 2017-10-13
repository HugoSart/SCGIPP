package scgipp.service.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Buy extends scgipp.data.hibernate.Entity{

    @Column
    private User user;

    @Column
    private Date date;

    @OneToMany
    private List<EstimativeBuy> estimativeBuyList = new ArrayList<>();

    public Buy(){}

    public Buy(User user, Date date) {
        this.user = user;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addEstimativeBuy(EstimativeBuy estimativeBuy){
        estimativeBuyList.add(estimativeBuy);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
