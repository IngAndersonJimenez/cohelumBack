package com.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TpConfiguracion", schema = "cohelum")
public class SettingTP {

    @Id
    @Column(name = "idTpConfiguracion")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idSettingTP;

    @Column(name = "artefacto")
    private String artefact;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "valor1")
    private String value1;

    @Column(name = "valor2")
    private String value2;

    @Column(name = "valor3")
    private String value3;

    @Column(name = "valor4")
    private String value4;

    @Column(name = "activo")
    private Boolean active;

}
