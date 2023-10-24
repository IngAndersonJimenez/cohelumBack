package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "persona", schema = "cohelum")
public class Person extends GeneralEntityAudit {

    @Id
    @Column(name = "idPersona")
    private Integer idPerson;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String surName;

}
