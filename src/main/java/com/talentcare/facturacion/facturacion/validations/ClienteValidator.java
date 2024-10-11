package com.talentcare.facturacion.facturacion.validations;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.talentcare.facturacion.facturacion.models.entity.Cliente;

@Component
public class ClienteValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Cliente.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Cliente cliente = (Cliente) target;

        ValidationUtils.rejectIfEmpty(errors, "nombre", "el campo nomnbre no puede ser vacio");

        if (cliente.getId() == null) {
            errors.rejectValue("id", "valor invalido del id");
        }

        if (!cliente.getNombre().matches("([a-z,A-Z]{1,15}[ ])?[a-z,A-Z]{1,15}")) {
            errors.rejectValue("nombre", "El nombre no es valido");
        }
        if (!cliente.getNumeroTelefono().matches("[0-9]{10}")) {
            errors.rejectValue("numeroTelefono", "el numero debe ser del 1o digitos");
        }
        if (cliente.getDiaCreacion() == null) {
            errors.rejectValue("diaCreacion", "dia de creacion debe ser valido");
        } else {
            // Validar el formato de la fecha
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false); // Asegura que el formato debe ser exacto
            try {
                dateFormat.parse(dateFormat.format(cliente.getDiaCreacion()));
            } catch (ParseException e) {
                errors.rejectValue("diaCreacion", "El formato de la fecha debe ser yyyy-MM-dd");
            }
        }

    }

}
