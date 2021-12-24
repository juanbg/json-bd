package io.juanbg.jsoncrud.dto.containers;

import io.juanbg.jsoncrud.dto.Customer;
import io.juanbg.jsoncrud.dto.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class GetOneContainer extends Payload implements Serializable {
    private static final long serialVersionUID = -2862366222416339222L;
    private Customer customer;

}
