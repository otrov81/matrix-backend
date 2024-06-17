package com.matrix.matrix.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", length = 30)
    private String name;
    @Column(name = "nachname", length = 30)
    private String nachname;
    @Column(name = "email", length = 40)
    private String email;
    @Column(name = "created")
    private Date created;


    public String getName() {
        if ( ("xxx").equals(name) ) {
            return "eeeeeeeeeeeee";
        }else {
            return name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}
