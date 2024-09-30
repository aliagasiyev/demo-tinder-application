package az.edu.turing.demotinderapplication.model.dto.request;


import jakarta.validation.constraints.NotBlank;

public record UserRequest(

        @NotBlank()
        String username,
        @NotBlank
        String email,
        @NotBlank
        String fullName,
        @NotBlank
        String password,
        @NotBlank()
        String photoUrl
) {
}
