package com.kyle.mission.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "programs", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "name"
    })
})
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @Column(length = 100)
    private String name;

    private String theme;

    private String prgm_desc;

    @Column(length = 4000)
    private String prgm_detail;

    @ManyToOne
    @JoinColumn
    private Region region;

    public Program(String name, String theme, String prgm_desc, String prgm_detail) {
        this.name = name;
        this.theme = theme;
        this.prgm_desc = prgm_desc;
        this.prgm_detail = prgm_detail;
    }
}