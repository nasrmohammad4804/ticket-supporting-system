package com.nasr.supportingsystemproject.domain;

import com.nasr.supportingsystemproject.base.domain.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseEntity<Long,String> {

    private String name;
}
