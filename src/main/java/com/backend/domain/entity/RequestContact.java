package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import com.backend.domain.entity.generic.Reason;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "solicitud", schema = "cohelum")
public class RequestContact extends GeneralEntityAudit {

    @Id
    @Column(name = "idSolicitud")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idRequest;

    @Column(name = "nombreContacto")
    private String nameContact;

    @Column(name = "correoElectronico")
    private String email ;

    @Column(name = "motivo")
    private Reason reason;
    @Column(name = "adjunto")
    private String attach;
    @Column(name = "comentario")
    private String comment;
    @Column(name = "telefono")
    private String cellphone;

    @Column(name = "departamento")
    private String department;

    @Column(name = "ciudad")
    private String city;


}
