package com.ex.youtubekurs.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner runner(StudentRepository studentRepository) {
        return args -> {
            Student ibo = new Student(
                    "Ibo Eren",
                    LocalDate.of(2001, Month.AUGUST, 13),
                    "korkmazie@gmail.com"
            );
            Student alex = new Student(
                    "Alex Aren",
                    LocalDate.of(2005, Month.AUGUST, 13),
                    "alexaren@gmail.com"
            );
            studentRepository.saveAll(
                    List.of(ibo, alex)
            );
        };
    }
}
