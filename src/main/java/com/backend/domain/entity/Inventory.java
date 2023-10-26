package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "inventario", schema = "cohelum")
public class Inventory extends GeneralEntityAudit {

    @Id
    @Column(name = "idInventario")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idInventory;

    @Column(name = "nombre")
    private String name;

    @Column(name = "activo")
    private boolean active;

    @Column(name = "precio")
    private Integer price;

    @Column(name = "unidadesDisponibles")
    private Integer unitsAvailable;

}
