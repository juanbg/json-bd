package io.juanbg.jsoncrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderResponse implements Serializable {
    private static final long serialVersionUID = -4613829577085977187L;

    private String message;
    private boolean status;
    private String uid;
    private Integer code;

    public static HeaderResponse createSuccessHeader(String uid){
        return new HeaderResponse("Success operation", true, uid, 200);
    }

    public static HeaderResponse createFailResponse(String uid, int i, String message) {
        return new HeaderResponse("Error in operation: " + message, false, uid, i);
    }
}
