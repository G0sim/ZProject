package com.example.zproject.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CheckMailRequest {
    @Pattern(regexp = "[a-z0-9]+@[a-zA-Z]+[.]+[a-z]+")
    @NotNull
    String email;
}
