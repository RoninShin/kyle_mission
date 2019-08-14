package com.kyle.mission.common.jpa;

import java.time.LocalDateTime;
 
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
 
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
 
import lombok.Getter;
import lombok.Setter;
 
/**
 * BaseEntity 추상클래스
 * 해당 추상클래스를 상속할때 @Tablegenerator는 상속하는 클래스에서 정의해야함
 * 또한 id field의 칼럼속성도 필요할때에 재정의해야함
 *
 */
@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
@Setter
@Getter
public abstract class BaseEntity<T extends BaseEntity<?>> implements Comparable<T>{
    
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator = "SEQ_GENERATOR")
    private long id;
    
    @Column(name="CREATED_DATE",nullable=false,updatable=false)
    @CreatedDate
    private LocalDateTime createdDate;
    
    @Column(name="UPDATED_DATE",nullable=false)
    @LastModifiedDate
    private LocalDateTime modifiedDate;
 
    @Override
    public int compareTo(T o) {
        Long result = getId()-o.getId();
        return result.intValue();
    }
    
    // @CreatedBy, @LastModifiedBy - 스프링 시큐리티의 Principal을 사용
}