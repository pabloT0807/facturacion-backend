package com.talentcare.facturacion.facturacion.DAO;

import java.rmi.UnexpectedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import com.talentcare.facturacion.facturacion.models.entity.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ClienteDaoImpl implements IClienteDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return em.createQuery("from Cliente").getResultList(); // nombre de la Entidad no de la base de datos
    }

    @Override
    @Transactional // hara una modificacion a la db
    public void save(Cliente cliente) {
        if (cliente.getId() != null && cliente.getId() > 0) {
            // actualizar
            em.merge(cliente);

        } else {
            em.persist(cliente);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return em.find(Cliente.class, id);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            em.remove(this.findOne(id));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
