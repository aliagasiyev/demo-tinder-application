package az.edu.turing.demotinderapplication.model.dto.request;


import jakarta.validation.constraints.NotBlank;

public record UserRequest(

        @NotBlank()
        String name,

        @NotBlank()
        byte [] photoUrl
) {
}
