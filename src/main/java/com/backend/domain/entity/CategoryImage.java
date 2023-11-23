package com.backend.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "inventario", schema = "cohelum")
public class CategoryImage {

    @Id
    @Column(name = "idCategoriaImagen")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idCategoryImage;

    @Column(name = "imagen", length = 100000)
    private String image;

    @Column(name = "activo")
    private boolean active;

    @Column(name = "idCategoria")
    private Integer idCategory;

}
