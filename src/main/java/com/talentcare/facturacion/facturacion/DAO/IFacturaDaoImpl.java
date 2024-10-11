package com.talentcare.facturacion.facturacion.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.talentcare.facturacion.facturacion.models.entity.Cliente;
import com.talentcare.facturacion.facturacion.models.entity.Factura;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class IFacturaDaoImpl implements IFacturaDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Factura> findAll() {
        return em.createQuery("from Factura").getResultList();
    }

    @Override
    @Transactional
    public void save(Factura factura) {
        if (factura.getId() != null && factura.getId() > 0) {
            try {
                em.merge(factura); // actualizar
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                em.persist(factura); // nuevo
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findOne(Long id) {
        return em.find(Factura.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            em.remove(this.findOne(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
