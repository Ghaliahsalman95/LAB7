package com.example.lab7.Model;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class Student {
    @NotNull(message = "Name can not be null")
    @Size(min=3,max = 10,message = "Name length must be in range 3-10")
    private String name;
    @Size(min=10,max=10,message = "ID length must be 10")
    @NotNull(message = "ID can not be null")
    private String ID;
    @NotNull(message = "Student must be take course")
    private Course []courses;
    private String[] codeCourse;//if student delete course save  it code



}
