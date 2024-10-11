package com.talentcare.facturacion.facturacion.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "facturas")
public class Factura {

    private static final long serialVersionUID = -854058574920998469L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "21")
    private Long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fecha;

    @Nonnull
    private double monto;

    private String detalle;

    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // muchas facturas un cliente un cliente puede tener muchas //
                                                         // facturas
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Cliente cliente;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public double getMonto() {
        return monto;
    }

    public String getDetalle() {
        return detalle;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
