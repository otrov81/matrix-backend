package com.matrix.matrix.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "kab")
public class Kab {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private Date erstellt;
    private Date geaendert;
    private String ersteller;
    private String mandant;
    private String kundennr;
}
