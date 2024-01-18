package com.mariomanhique.demo.gradle.kotlin.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;

import static java.time.Month.AUGUST;

@Configuration
public class StudentLoader {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository
    ){
        return args -> {
            Student mario = new Student(
                    "Mario Manhique",
                    "mario@gmail.com",
                    LocalDate.of(1999, AUGUST,24)
            );

            Student sandra = new Student(
                    "Sandra Manhique",
                    "sandra@gmail.com",
                    LocalDate.of(1981, AUGUST,3)
            );
            studentRepository.saveAll(List.of(mario,sandra));
        };
    }
}
