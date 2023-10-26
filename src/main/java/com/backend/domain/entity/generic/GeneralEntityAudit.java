package com.backend.domain.entity.generic;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.Date;

@MappedSuperclass
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public abstract class GeneralEntityAudit {

    @Column(name = "fechaCreacion", updatable = false)
    private Date highDate;

    @Column(name = "fechaModificacion")
    private Date modificationDate;

    @PrePersist
    public void setCreationDate() {
        this.highDate = new Date();
    }

}
