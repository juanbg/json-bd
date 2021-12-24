package io.juanbg.jsoncrud.controller;

import io.juanbg.jsoncrud.bo.CustomersBO;
import io.juanbg.jsoncrud.dto.Customer;
import io.juanbg.jsoncrud.dto.HeaderResponse;
import io.juanbg.jsoncrud.dto.ResponseDTO;
import io.juanbg.jsoncrud.utils.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.util.UUID;

@RestController
@Slf4j
public class CustomersController {

    @Autowired
    private CustomersBO customersBO;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getCustomer(@PathVariable(name = "id") Integer id, @RequestHeader(value = "uid") String uid) {
        log.debug("{} :: Process of read begins for the user {}", uid, id);
        if (uid.isEmpty())
            uid = UUID.randomUUID().toString();
        try {
            return new ResponseEntity<>(customersBO.readUser(uid, id), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO(null, HeaderResponse.createFailResponse(uid, 500, e.getMessage()));
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getCustomers(@RequestHeader(value = "uid") String uid) {
        log.debug("{} :: Process of read begins for all users", uid);
        if (uid.isEmpty())
            uid = UUID.randomUUID().toString();
        try {
            return new ResponseEntity<>(customersBO.readUser(uid, null), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO(null, HeaderResponse.createFailResponse(uid, 500, e.getMessage()));
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> postCustomer(@RequestHeader(value = "uid") String uid, @RequestBody Customer customer) {
        log.debug("{} :: Process of creation begins for new user", uid);
        if (uid.isEmpty())
            uid = UUID.randomUUID().toString();

        try {
            BeanValidator.validate(customer);
            customersBO.createUser(uid, customer);
        } catch (Exception e) {
            return BeanValidator.validaException(e, uid);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> putCustomer(@PathVariable(name = "id") Integer id, @RequestHeader(value = "uid") String uid, @RequestBody Customer customer) {
        log.debug("{} :: Process of update begins for the user {}", uid, id);
        if (uid.isEmpty())
            uid = UUID.randomUUID().toString();

        try {
            BeanValidator.validate(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return BeanValidator.validaException(e, uid);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteCustomer(@PathVariable(name = "id") Integer id, @RequestHeader(value = "uid") String uid) {
        log.debug("{} :: Process of deletion begins for the user {}", uid, id);
        if (uid.isEmpty())
            uid = UUID.randomUUID().toString();
        try {
            customersBO.deleteUser(uid, id);
        } catch (Exception e) {
            return BeanValidator.validaException(e, uid);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
