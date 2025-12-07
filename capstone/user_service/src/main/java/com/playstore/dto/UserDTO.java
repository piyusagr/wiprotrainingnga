package com.playstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
//@AllArgsConstructor
public class UserDTO {
    @NotBlank private String username;
    @NotBlank @Email private String email;
    @NotBlank @Size(min=6) private String password;
    private String role="USER";
}




