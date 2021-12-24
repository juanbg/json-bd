package io.juanbg.jsoncrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO implements Serializable {
    private static final long serialVersionUID = 3018239627367217566L;

    private Payload payload;
    private HeaderResponse headerResponse;


}
