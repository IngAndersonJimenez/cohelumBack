package com.backend.domain.entity;


import com.backend.domain.entity.generic.GeneralEntityAudit;
import com.backend.domain.entity.generic.ReasonEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "garantía", schema = "cohelum")
public class Warranty extends GeneralEntityAudit {

    @Id
    @Column(name = "idSolicitudG")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idRequestG;

    @Column(name = "nombreContacto")
    private String nameContact;

    @Column(name = "tipoDocumento")
    private String typeDocument ;

    @Column(name = "documento")
    private String document ;

    @Column(name = "correo")
    private String email;

    @Column(name = "distribuidor")
    private String distributor;

    @Column(name = "numeroFactura")
    private String number_bill;

    @Column(name = "telefono")
    private String cellphone;

    @Column(name = "fechaCompra")
    private String date;

    @Column(name = "productoAdquirido")
    private String product;

    @Column(name = "adjuntoProducto")
    private String attach;


}
