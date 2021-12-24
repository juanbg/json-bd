package io.juanbg.jsoncrud.bo;

import io.juanbg.jsoncrud.dao.CustomerDAO;
import io.juanbg.jsoncrud.dto.Customer;
import io.juanbg.jsoncrud.dto.HeaderResponse;
import io.juanbg.jsoncrud.dto.ResponseDTO;
import io.juanbg.jsoncrud.dto.containers.GetAllContainer;
import io.juanbg.jsoncrud.dto.containers.GetOneContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
@Component
@Slf4j
public class CustomersBOImpl implements Serializable, CustomersBO {

    private static final long serialVersionUID = -7008363128660545452L;

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public ResponseDTO readUser(String uid, Integer id) throws IOException {
        ResponseDTO responseDTO;
        if(id == null){
            responseDTO = new ResponseDTO(new GetAllContainer(customerDAO.readAllCustomer(uid)),
                    HeaderResponse.createSuccessHeader(uid));
        }else{

            responseDTO = new ResponseDTO(new GetOneContainer(customerDAO.readCustomer(uid, id)),
                    HeaderResponse.createSuccessHeader(uid));
        }

        return responseDTO;
    }

    @Override
    public void createUser(String uid, Customer customer) throws Exception {
        try {
            customerDAO.creationCustomer(uid, customer);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void updateUser(String uid, Integer id, Customer customer) throws IOException {
        Customer oldCustumer = customerDAO.readCustomer(uid, id);

        oldCustumer.setName(customer.getName());
        oldCustumer.setPaternalLastName(customer.getPaternalLastName());
        oldCustumer.setMaternalLastname(customer.getMaternalLastname());
        oldCustumer.setEmail(customer.getEmail());
        oldCustumer.setBirthYear(customer.getBirthYear());
        oldCustumer.setGender(customer.getGender());


        customerDAO.updateCustomer(uid, oldCustumer, id);


    }

    @Override
    public void deleteUser(String uid, Integer id) throws IOException {
            customerDAO.deleteCustomer(uid, id);
    }
}
