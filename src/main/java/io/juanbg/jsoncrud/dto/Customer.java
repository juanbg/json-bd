package io.juanbg.jsoncrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    private static final long serialVersionUID = 3058384677717649917L;

    private Integer id;

    @NotNull(message = "The property 'name' is needed")
    private String name;
    @NotNull(message = "The property 'paternalLastName' is needed")
    private String paternalLastName;
    @NotNull(message = "The property 'maternalLastname' is needed")
    private String maternalLastname;
    @NotNull(message = "The property 'email' is needed")
    private String email;
    @NotNull(message = "The property 'birthYear' is needed")
    private LocalDate birthYear;
    @NotNull(message = "The property 'gender' is needed")
    private char gender;
}
