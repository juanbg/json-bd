package io.juanbg.jsoncrud.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CustomerDB implements Serializable {
    private static final long serialVersionUID = -3323807658372051212L;
    List<Customer> customers;
}
