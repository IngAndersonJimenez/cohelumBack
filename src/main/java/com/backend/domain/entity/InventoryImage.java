package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "inventarioImagen", schema = "cohelum")
public class InventoryImage extends GeneralEntityAudit {

    @Id
    @Column(name = "idInventarioImagen")
    private Integer idInventoryImage;

    @Column(name = "imagen")
    private String image;

    @Column(name = "activo")
    private boolean active;

    @Column(name = "idInventario")
    private Integer idInventory;

}
