package io.juanbg.jsoncrud.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.juanbg.jsoncrud.dto.Customer;
import io.juanbg.jsoncrud.dto.CustomerDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Component
@DependsOn("objectMapper")
public class CustomerDAOImpl implements Serializable, CustomerDAO {
    private static final long serialVersionUID = -8678846366173929257L;

    @Autowired
    private ObjectMapper mapper;

    @Value("${physical.route}")
    private String route;

    @Override
    public void creationCustomer(String uid, Customer customer) throws Exception {
        log.debug("{} :: Creation processes begins", uid);
        CustomerDB bd = mapper.readValue(Paths.get(route).toFile(), CustomerDB.class);

        int id = this.choseId(uid, bd.getCustomers());
        customer.setId(id);
        bd.getCustomers().add(customer);

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Paths.get(route).toFile(), false)));
        mapper.writeValue(out, bd);
    }

    @Override
    public Customer readCustomer(String uid, Integer id) throws IOException {
        log.debug("{} :: Reading processes begins for id {}", uid, id);

        CustomerDB bd = mapper.readValue(Paths.get(route).toFile(), CustomerDB.class);

        return bd.getCustomers().stream().filter(customer -> customer.getId().equals(id)).findFirst().orElse(null);

    }

    @Override
    public List<Customer> readAllCustomer(String uid) throws IOException {
        log.debug("{} :: Reading processes begins for all users ", uid);
        CustomerDB bd = mapper.readValue(Paths.get(route).toFile(), CustomerDB.class);

        return bd.getCustomers();
    }

    @Override
    public void updateCustomer(String uid, Customer newCustomer) throws IOException {
        CustomerDB bd = mapper.readValue(Paths.get(route).toFile(), CustomerDB.class);

        bd.getCustomers().removeIf(customer -> newCustomer.getId().equals(customer.getId()));
        bd.getCustomers().add(newCustomer);

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Paths.get(route).toFile(), false)));
        mapper.writeValue(out, bd);
    }

    @Override
    public void deleteCustomer(String uid, Integer id) throws IOException {
        CustomerDB bd = mapper.readValue(Paths.get(route).toFile(), CustomerDB.class);

        bd.getCustomers().removeIf(customer -> customer.getId().equals(id));

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Paths.get(route).toFile(), false)));
        mapper.writeValue(out, bd);
    }

    private int choseId(String uid, List<Customer> customers) {
        log.debug("{} :: Cheking next id", uid);

        return customers.stream().min(Comparator.comparing(Customer::getId)).orElseThrow(NoSuchElementException::new).getId()+1;
    }
}