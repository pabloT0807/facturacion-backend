package com.talentcare.facturacion.facturacion.DAO;

import java.util.List;

import com.talentcare.facturacion.facturacion.models.entity.Factura;

public interface IFacturaDao {

    public List<Factura> findAll();

    public void save(Factura factura);

    public Factura findOne(Long id);

    public void delete(Long id);
}
