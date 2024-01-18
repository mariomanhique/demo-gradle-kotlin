package com.mariomanhique.demo.gradle.kotlin.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record StudentRequest(
        @NotBlank(message = "Name can't be blank")
        String name,
        @NotBlank(message = "Email can't be blank")
        String email,
        @NotBlank(message = "Birthdate can't be blank")
        LocalDate dob,
        @NotBlank(message = "Age can't be blank")
        Integer age

) {
}
