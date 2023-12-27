package com.backend.domain.entity;


import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "inventarioComentario", schema = "cohelum")
public class InventoryComment extends GeneralEntityAudit {

    @Id
    @Column(name = "idInventarioComentario")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idInventoryComment;

    @Column(name = "calificacion")
    private Integer qualification;

    @Column(name = "activo")
    private boolean active;

    @Column(name = "reseña")
    private String review;

    @Column(name = "nombre")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "idInventario")
    private Integer idInventory;
}
