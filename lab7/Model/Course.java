package com.example.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class Course {
    @NotNull(message = "Name can not be null")
    @Size(min=3,max = 10,message = "Name length must be in range 3-10")
    private String name;
    @NotNull(message = "Code can not be null")
    @Size(min = 6,max = 6,message = "Code must be 6 character")
    private String code;
    @NotNull(message = "kind can not be null")
    @Pattern(regexp = "In-Person|Online")
    private String kind;//online or in person
    @NotNull(message = "score can not be null ")
    @Min(0)@Max(100)
    private double score;
    private Integer hours;
    @NotNull(message = "absence not null")
    @Positive(message = "absence should be positive")
    private Integer absence;
    @AssertFalse(message = "must be extra Course False")
    private boolean extraCourse;
}
