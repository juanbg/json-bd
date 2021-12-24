package io.juanbg.jsoncrud.bo;

import io.juanbg.jsoncrud.dto.Customer;
import io.juanbg.jsoncrud.dto.ResponseDTO;

import java.io.IOException;


public interface CustomersBO {
    ResponseDTO readUser(String uid, Integer id) throws IOException;

    void createUser(String uid, Customer customer) throws Exception;

    void updateUser(String uid, Integer id, Customer customer) throws IOException;

    void deleteUser(String uid, Integer id) throws IOException;
}
