package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private BigDecimal price;

    @Column(name = "unidadesDisponibles")
    private Integer unitsAvailable;

    @Column(name = "idSubCategoria")
    private Integer idSubCategory;

    @Column(name = "descuento")
    private Integer discount;

}
