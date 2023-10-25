package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "inventarioDetalle", schema = "cohelum")
public class InventoryDetails extends GeneralEntityAudit {

    @Id
    @Column(name = "idInventarioDetalle")
    private Integer idInventoryDetails;

    @Column(name = "caracteristicas")
    private String characteristic;

    @Column(name = "fichaTecnica")
    private String datasheet;


}
