package com.kyle.mission.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
@Getter 
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "programs")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NaturalId(mutable=true)
    @Column(length = 100)
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Program> programs;

    public Region(String name, Program... programs) {
        this.name = name;
        this.programs = Stream.of(programs).collect(Collectors.toSet());
        this.programs.forEach(x -> x.setRegion(this));
    }
}