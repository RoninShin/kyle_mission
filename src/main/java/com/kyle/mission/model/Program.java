package com.kyle.mission.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "programs"
// , uniqueConstraints = {
//     @UniqueConstraint(columnNames = {
//         "name"
//     })
// }
)
@Getter 
@Setter
@NoArgsConstructor
@ToString(exclude = {"region", "prgm_desc", "prgm_detail"})
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Region region;

    // Join 테이블이 Json결과에 표시되지 않도록 처리.
    protected Region getRegion() {
        return region;
    }

    public Program(String name, String theme, String prgm_desc, String prgm_detail) {
        this.name = name;
        this.theme = theme;
        this.prgm_desc = prgm_desc;
        this.prgm_detail = prgm_detail;
    }
}