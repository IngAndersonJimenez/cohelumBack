package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "inventarioImagen", schema = "cohelum")
@ToString
public class InventoryImage extends GeneralEntityAudit {

    @Id
    @Column(name = "idInventarioImagen")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idInventoryImage;

    @Column(name = "imagen", length = 100000)
    private String image;

    @Column(name = "activo")
    private boolean active;

    @Column(name = "idInventario")
    private Integer idInventory;

}
