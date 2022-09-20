package com.thisname.springsoftdealer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @OneToOne
    @Getter @Setter
    private User user;

    @Getter @Setter
    private String accessLevel;

    @Getter @Setter
    private Date time;

    public Admin() {
    }

    public Admin(User user, String accessLevel, Date time) {
        this.user = user;
        this.accessLevel = accessLevel;
        this.time = time;
    }


}
