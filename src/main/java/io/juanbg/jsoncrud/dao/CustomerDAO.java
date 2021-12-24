package io.juanbg.jsoncrud.dao;

import io.juanbg.jsoncrud.dto.Customer;

import java.io.IOException;
import java.util.List;


public interface CustomerDAO {
    void creationCustomer(String uid, Customer customer) throws Exception;
    Customer readCustomer(String uid, Integer id) throws IOException;
    List<Customer> readAllCustomer(String uid) throws IOException;
    void updateCustomer(String uid, Customer customer) throws IOException;
    void deleteCustomer(String uid, Integer id) throws IOException;

}
