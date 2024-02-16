package com.backend.domain.entity.generic;

public enum ReasonEnum {
    GARANTIA(1, "garantia"),
    CONTACTO_GENERAL(2, "contactoGeneral"),

    SUSCRIPCION(3, "Suscripcion");

    private Integer id;
    private String descrption;


    private ReasonEnum(Integer id, String descrption) {
        this.id = id;
        this.descrption = descrption;
    }

    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    public String getDescrption() {
        return descrption;
    }

    void setDescrption(String descrption) {
        this.descrption = descrption;
    }
}
