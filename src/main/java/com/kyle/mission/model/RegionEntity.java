package com.kyle.mission.model;

import java.io.Serializable;
 
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
 
import com.kyle.mission.common.jpa.BaseEntity;
 
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Entity
@Table(name="KYLE_REGION"
        ,indexes=@Index(columnList="REGION_NAME",unique=false))
@AttributeOverride(name = "id",column = @Column(name = "REGION_ID"))
@TableGenerator(name="SEQ_GENERATOR",table="TB_SEQUENCE",
                pkColumnName="SEQ_NAME",pkColumnValue="REGION_MAIN_SEQ",allocationSize=1)
@Getter
@Setter
@ToString
public class RegionEntity extends BaseEntity<RegionEntity> implements Serializable{
    
    private static final long serialVersionUID = 1864304860822295551L;
    
    @Column(name="REGION_NAME",nullable=false)
    private String regionName;
    
}