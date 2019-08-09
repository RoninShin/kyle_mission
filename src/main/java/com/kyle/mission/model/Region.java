package com.kyle.mission.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "regions", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "name"
    })
})
@Data 
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "programs")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @Column(length = 100)
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<Program> programs;

    public Region(String name, Program... programs) {
        this.name = name;
        this.programs = Stream.of(programs).collect(Collectors.toSet());
        this.programs.forEach(x -> x.setRegion(this));
    }
}