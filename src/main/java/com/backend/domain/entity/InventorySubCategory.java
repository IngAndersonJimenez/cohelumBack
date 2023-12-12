package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "inventarioSubCategoria", schema = "cohelum")
public class InventorySubCategory extends GeneralEntityAudit {

    @Id
    @Column(name = "idSubCategoria")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSubCategory;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "activo")
    private boolean active;

    @Column(name = "idCategoria")
    private Integer idCategory;

}
