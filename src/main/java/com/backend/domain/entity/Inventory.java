package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "inventario", schema = "cohelum")
public class Inventory extends GeneralEntityAudit {

    @Id
    @Column(name = "idInventario")
    private Integer idInventory;

    @Column(name = "nombre")
    private String name;

    @Column(name = "activo")
    private boolean active;

    @Column(name = "precio")
    private Integer price;

    @Column(name = "unidadesDisponibles")
    private Integer unitsAvailable;

    @Column(name = "idCategoria")
    private Integer idCategory;

}
