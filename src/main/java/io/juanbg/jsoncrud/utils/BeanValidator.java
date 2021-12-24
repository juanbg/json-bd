package io.juanbg.jsoncrud.utils;



import io.juanbg.jsoncrud.dto.HeaderResponse;
import io.juanbg.jsoncrud.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.*;
import java.util.Set;
import java.util.stream.Collectors;

public class BeanValidator {

    public static <T> void validate(T object, Class<?>... groups) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> validatorSet = validator.validate(object, groups);

        validatorSet.forEach(cv -> {
            throw new ConstraintViolationException(validatorSet);
        });
    }


    public static ResponseEntity<ResponseDTO> validaException(Object o, String uid) {

        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO respuestaDTO = new ResponseDTO();

        if(o instanceof ConstraintViolationException) {
            ConstraintViolationException e = (ConstraintViolationException) o;
            respuestaDTO.setHeaderResponse(HeaderResponse.createFailResponse(uid, 400, e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(" | "))));

            response = new ResponseEntity<>(respuestaDTO, HttpStatus.BAD_REQUEST);
        }else if (o instanceof Exception) {
            Exception e = (Exception) o;
            respuestaDTO.setHeaderResponse(new HeaderResponse(e.getMessage(), false,  uid, 0));
            response = new ResponseEntity<>(respuestaDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


}
