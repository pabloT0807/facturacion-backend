package com.talentcare.facturacion.facturacion.DTO;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ClienteDTO {

    private String nombre;
    private String numeroTelefono;
    private Date diaCreacion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Date getDiaCreacion() {
        return diaCreacion;
    }

    public void setDiaCreacion(Date diaCreacion) {
        this.diaCreacion = diaCreacion;
    }

}
