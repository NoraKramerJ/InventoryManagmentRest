package com.cydeo.InventoryManagementRest.entity;


import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@Where(clause = "is_deleted = false")
public class Role extends BaseEntity {

    private String description;

}
