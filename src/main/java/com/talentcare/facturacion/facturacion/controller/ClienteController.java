package com.talentcare.facturacion.facturacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.talentcare.facturacion.facturacion.DAO.IClienteDao;
import com.talentcare.facturacion.facturacion.DTO.ClienteDTO;
import com.talentcare.facturacion.facturacion.models.entity.Cliente;
import com.talentcare.facturacion.facturacion.validations.ClienteValidator;

@RestController
@SessionAttributes("cliente")
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:4200") // Aquí colocas la URL del frontend (Angular)
public class ClienteController {

    @Autowired
    private IClienteDao clienteDao;

    @Autowired
    private ClienteValidator clienteValidator;

    @GetMapping("/obtenerClientes")
    public List<Cliente> clienteList() {
        System.out.println("Method clienteList");
        return clienteDao.findAll();
    }

    @PostMapping("/guardar-cliente")
    public ResponseEntity<?> createCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente clienteAux = new Cliente();

        try {
            clienteAux.setNombre(clienteDTO.getNombre());
            clienteAux.setNumeroTelefono(clienteDTO.getNumeroTelefono());
            clienteAux.setDiaCreacion(clienteDTO.getDiaCreacion());

            clienteDao.save(clienteAux);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("comunicarse con el admin");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("cliente creado correctamente");
    }

    @GetMapping("/clientePorId/{id}")
    public Cliente getClientePorId(@PathVariable(name = "id", required = true) Long id) {
        return clienteDao.findOne(id);
    }

    @DeleteMapping("/delete-cliente/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable(name = "id", required = true) Long id) {
        try {
            clienteDao.delete(id);
            return ResponseEntity.status(100).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("errror");
        } catch (UnexpectedRollbackException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("id no coincide");
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error desconocido");
        }
    }
}
