package com.example.mobilele.model.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginBindingModel {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String password;

    public UserLoginBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
