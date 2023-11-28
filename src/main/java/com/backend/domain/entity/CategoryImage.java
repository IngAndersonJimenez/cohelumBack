package com.backend.domain.entity;

import com.backend.domain.entity.generic.GeneralEntityAudit;
import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Data
@Table(name = "categoriaImagen", schema = "cohelum")
public class CategoryImage extends GeneralEntityAudit {

    @Id
    @Column(name = "idCategoriaImagen")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idCategoryImage;

    @Column(name = "imagen", length = 100000)
    private String photo;

    @Column(name = "activo")
    private boolean active;

    @Column(name = "idCategoria")
    private Integer idCategory;

}
