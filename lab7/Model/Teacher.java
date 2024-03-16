package com.example.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class Teacher {
    @NotNull(message = "Name can not be null")
    @Size(min=3,max = 10,message = "Name length must be in range 3-10")
    private String name;
    @Size(min=10,max=10,message = "ID length must be 10")
    @NotNull(message = "ID can not be null")
    private String ID;
    @NotNull (message = "Salary can not be null")
    @Min(3000)@Max(10000)
    private double salary;//no need but to collect
    private Course course;

}
