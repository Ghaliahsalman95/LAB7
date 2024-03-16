package com.example.lab7.Controller;

import com.example.lab7.APIResponse.APIResponse;
import com.example.lab7.Model.Course;
import com.example.lab7.Service.CourseService;
import com.example.lab7.Service.StudentService;
import com.example.lab7.Service.TeacherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    //create injection
    private final CourseService courseService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        if (courseService.getCourses().isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("Empty Course"));
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourses());
    }

    @PostMapping("/add-course")
    public ResponseEntity addCourse(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Course " + course.getName() + "Course Code " + course.getCode() + " Added successfully"));
    }

    @PutMapping("/update-course/{course_code}")
    public ResponseEntity update(@RequestBody @Valid Course course, @PathVariable String course_code, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (courseService.updateCourse(course_code, course))
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Course" + course.getName() + " updated successfully"));
        return ResponseEntity.status(400).body(new APIResponse("Course code" + course_code + "NOT FOUND IT"));
    }

    @DeleteMapping("/delete/{course_code}")
    public ResponseEntity delete(@PathVariable String course_code) {
        if (courseService.deleteCourse(course_code))
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Course" + course_code + "Deleted successfully"));

        else return ResponseEntity.status(400).body(new APIResponse("Course" + course_code + "NOT FOUND IT"));
    }

    ///-------------------------------------------------------------
    @GetMapping("/who-add-course/{course_code}")
    public ResponseEntity whoAddCourse(@PathVariable String course_code) {
        if (courseService.whoAddCourse(course_code).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("THERE IS NO STUDENT ADDED THIS COURSE " + course_code));

        else return ResponseEntity.status(HttpStatus.OK).body(courseService.whoAddCourse(course_code));
    }
    //---------------------------------------

    @GetMapping("/who-delete-course/{course_code}")
    public ResponseEntity whoDelete(@PathVariable String course_code) {
        if (courseService.whoDelete(course_code).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("THERE IS NO STUDENT ADDED THIS COURSE " + course_code));

        else return ResponseEntity.status(HttpStatus.OK).body(courseService.whoDelete(course_code));
    }

    @GetMapping("/no-asign-teacher/{course_code}")
    public ResponseEntity noasignTeacher(@PathVariable String course_code) {
        if (courseService.noasignTeacher(course_code).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("THERE IS NO COURSE WITHOUT TEACHER ASSIGNED"));
        else return ResponseEntity.status(HttpStatus.OK).body(courseService.noasignTeacher(course_code));

    }

        @GetMapping("/who-teach/{course_code}")
    public ResponseEntity whoTeach(@PathVariable String course_code){
        if (courseService.whoTeach(course_code).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("THERE IS NO TEACHER TEACH THIS COURSE"+course_code));
        else return ResponseEntity.status(HttpStatus.OK).body(courseService.whoTeach(course_code));
        }
}
