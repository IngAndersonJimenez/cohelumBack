package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "inventarioCategoria", schema = "cohelum")
public class InventoryCategory extends GeneralEntityAudit {

    @Id
    @Column(name = "idCategoria")
    private Integer idCategory;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "activo")
    private boolean active;

}
