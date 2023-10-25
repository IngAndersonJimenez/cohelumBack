package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "idInventario", referencedColumnName = "idInventory")
    private Inventory inventory;

}
