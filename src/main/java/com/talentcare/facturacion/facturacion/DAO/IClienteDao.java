package com.talentcare.facturacion.facturacion.DAO;

import java.util.List;

import com.talentcare.facturacion.facturacion.models.entity.Cliente;

public interface IClienteDao {

    public List<Cliente> findAll();

    public void save(Cliente cliente);

    public Cliente findOne(Long id);

    public void delete(Long id);
}
