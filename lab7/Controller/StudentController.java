package com.example.lab7.Controller;

import com.example.lab7.APIResponse.APIResponse;
import com.example.lab7.Model.Course;
import com.example.lab7.Model.Student;
import com.example.lab7.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        if (studentService.getStudents().isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("Empty Student"));
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudents());
    }

    @PostMapping("/add-student")
    public ResponseEntity addCourse(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Student : " + student.getName() + "Student ID " + student.getID() + " Added successfully"));
    }

    @PutMapping("/update-student/{student_ID}")
    public ResponseEntity update(@RequestBody @Valid Student student, @PathVariable String student_ID, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (studentService.updateStudent(student_ID, student))
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Student" + student.getName() + " updated successfully"));
        return ResponseEntity.status(400).body(new APIResponse("Student ID" + student_ID + "NOT FOUND IT"));
    }

    @DeleteMapping("/delete/{student_ID}")
    public ResponseEntity delete(@PathVariable String student_ID) {
        if (studentService.deleteStudent(student_ID))
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Student ID" + student_ID + "Deleted successfully"));

        else return ResponseEntity.status(400).body(new APIResponse("Student" + student_ID + "NOT FOUND IT"));
    }

    @GetMapping("/get-top-course/{course_code}")
    public ResponseEntity getTopCourse(@PathVariable String course_code){
        if(studentService.getTopCourse(course_code).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("NO STUDENT HAS TOP SCORE IN COURSE CODE"+course_code));
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getTopCourse(course_code));
    }
    @GetMapping("/get-faild/{course_code}")
    public ResponseEntity getFaild(@PathVariable String course_code){
        if (studentService.getFaild(course_code).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("THERE IS NO STUDENT FAILED IN COURSE CODE"+course_code));
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getTopCourse(course_code));
    }

    @GetMapping("/study-online-In-person/{course_code}/{kind}")
    public ResponseEntity study(@PathVariable String course_code,@PathVariable String kind){
        if (studentService.studyOnline_Inperson(course_code,kind).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("THERE IS NO STUDENT STUDY COURSE CODE"+course_code+" "+kind));
        return ResponseEntity.status(HttpStatus.OK).body(studentService.studyOnline_Inperson(course_code,kind));
        }

    @GetMapping("/no-absence-hours/{course_code}/{hours}")
    public ResponseEntity noAbsenceHours(@PathVariable String course_code,@PathVariable double hours){
        if (studentService.noabsencehours(course_code,hours).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("THERE IS NO STUDENT HAS NO ABSENCE HOURS IN COURSE CODE "+course_code));
        return ResponseEntity.status(HttpStatus.OK).body(studentService.noabsencehours(course_code,hours));
    }

}
