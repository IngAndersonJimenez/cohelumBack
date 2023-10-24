package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "usuario", schema = "cohelum")
public class User  extends GeneralEntityAudit {

    @Id
    @Column(name = "idUsuario")
    private Integer idUser;

    @Column(name = "correo")
    private String email;

    @Column(name = "clave")
    private String password;

}
