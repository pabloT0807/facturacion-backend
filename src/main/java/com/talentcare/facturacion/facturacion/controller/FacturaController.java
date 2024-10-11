package com.talentcare.facturacion.facturacion.controller;

import java.util.List;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentcare.facturacion.facturacion.DAO.IClienteDao;
import com.talentcare.facturacion.facturacion.DAO.IFacturaDao;
import com.talentcare.facturacion.facturacion.DTO.FacturaDTO;
import com.talentcare.facturacion.facturacion.models.entity.Cliente;
import com.talentcare.facturacion.facturacion.models.entity.Factura;

@RestController
@RequestMapping("/facturas")
@CrossOrigin(origins = "http://localhost:4200") // Aquí colocas la URL del frontend (Angular)
public class FacturaController {

    @Autowired
    private IFacturaDao facturaDao;

    @Autowired
    private IClienteDao clienteDao;

    // lista de facturas
    @GetMapping("/facturas")
    public List<Factura> facturaList() {
        return facturaDao.findAll();
    }

    @GetMapping("/facturas/{id}")
    public Factura getFacturaById(@PathVariable(name = "id") Long id) {
        return facturaDao.findOne(id);
    }

    @DeleteMapping("/facturas/{id}")
    public void deleteFactura(@PathVariable(name = "id") Long id) {
        try {
            facturaDao.delete(id);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @PostMapping("/guardarFactura")
    public ResponseEntity<?> createFactura(@RequestBody FacturaDTO facturaDTO) {
        Factura facturaAux = new Factura();
        Cliente clientrAux = new Cliente();

        try {
            facturaAux.setFecha(facturaDTO.getFecha());
            facturaAux.setMonto(facturaDTO.getMonto());
            facturaAux.setDetalle(facturaDTO.getDetalle());

            clientrAux = clienteDao.findOne(facturaDTO.getCliente());
            facturaAux.setCliente(clientrAux);

            facturaDao.save(facturaAux);

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("comunicarse con el admin");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("FACTURA CREADA EXITOSAMENTE");
    }

}
