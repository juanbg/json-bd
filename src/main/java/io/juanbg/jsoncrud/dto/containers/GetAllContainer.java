package io.juanbg.jsoncrud.dto.containers;

import io.juanbg.jsoncrud.dto.Customer;
import io.juanbg.jsoncrud.dto.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class GetAllContainer extends Payload implements Serializable  {
    private static final long serialVersionUID = 6715753088917570355L;

    List<Customer> customers;
}
