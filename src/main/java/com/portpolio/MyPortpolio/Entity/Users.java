package com.portpolio.MyPortpolio.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Users {

    @NotBlank(message = "Firstname is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
    private String FirstName;
    @NotBlank(message = "Lastname is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
    private String LastName;
    @NotBlank(message = "email cannot be empty")
    @Email(message = "enter valid mail")
    @Column(unique = true)
    @Id
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="users_roles",
            joinColumns = @JoinColumn(name="email")
    )
    private List<String> roles;
}
