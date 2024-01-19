package com.crm_test.crm_test.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class WebUser {

    @NotNull
    @Size(min = 5, message = "username must be at least 5 characters")
    private String username;

    @NotNull
    @Size(min = 8, message = "password must be at least 5 characters")
    private String password;

    @NotNull
    @Size(min = 1, message = "can't be empty")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "can't be empty")
    private String lastName;

    @NotNull
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "enter a valid email")
    @Size(min = 1, message = "can't be empty")
    private String email;
}
