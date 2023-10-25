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
    private Integer idInventoryComment;

    @Column(name = "calificacion")
    private Integer qualification;

    @Column(name = "activo")
    private boolean active;
}
