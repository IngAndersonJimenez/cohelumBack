package com.backend.domain.entity.generic;

public enum ReasonEnum {
    GARANTIA(1,"garantia"),
    CONTACTO_GENERAL(2,"contactoGeneral");

    private Integer id;
    private String descrption;


    private ReasonEnum(Integer id, String descrption) {
        this.id = id;
        this.descrption = descrption;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }
}
