package com.example.lab7.Controller;

import com.example.lab7.APIResponse.APIResponse;
import com.example.lab7.Model.Teacher;
import com.example.lab7.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        if (teacherService.get_Teachers().isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("Empty Teacher"));
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.get_Teachers());
    }

    @PostMapping("/add-teacher")
    public ResponseEntity addCourse(@RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Teacher : " + teacher.getName() + "Teacher ID " + teacher.getID() + " Added successfully"));
    }

    @PutMapping("/update-teacher/{teacher_ID}")
    public ResponseEntity update(@RequestBody @Valid Teacher teacher, @PathVariable String teacher_ID, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (teacherService.updateTeacher(teacher_ID, teacher))
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Teacher" + teacher.getName() + " updated successfully"));
        return ResponseEntity.status(400).body(new APIResponse("Teacher ID" + teacher_ID + "NOT FOUND IT"));
    }

    @DeleteMapping("/delete/{teacher_ID}")
    public ResponseEntity delete(@PathVariable String teacher_ID) {
        if (teacherService.deleteTeacher(teacher_ID))
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Teacher ID" + teacher_ID + "Deleted successfully"));

        else return ResponseEntity.status(400).body(new APIResponse("Teacher" + teacher_ID + "NOT FOUND IT"));
    }

    @GetMapping("/get-teach-in-Person-On-line/{course_code}/{kind}")
    public ResponseEntity getTeachInPerson_Online(@PathVariable String course_code,@PathVariable String kind){

        if (teacherService.getTeachInPerson_Online(course_code,kind).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("THERE IS NO TEACHER TEACH COURSE"+course_code+"KIND"+kind));
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getTeachInPerson_Online(course_code,kind));
    }

    @GetMapping("/get-list-tearcher-student/{course_code}/{teacher_ID}")
    public ResponseEntity getlistTearcherStudent(@PathVariable String course_code,@PathVariable String teacher_ID){
        if(teacherService.getlistTearcherStudent(course_code,teacher_ID).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("No Student assigned"));
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getlistTearcherStudent(course_code,teacher_ID));
    }
    @GetMapping("/get-student-extra-course/{course_code}/{teacher_ID}")
    public ResponseEntity getstudentExtracourse(@PathVariable String course_code,@PathVariable String teacher_ID){
        if(teacherService.getlistTearcherStudent(course_code,teacher_ID).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("No Student take extra classes in course code"+course_code+"with teacher ID"+teacher_ID));
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getstudentExtracourse(course_code,teacher_ID));
    }
@GetMapping("/assign-extra-course/{course_code}/{teacher_ID}")
    public ResponseEntity assignExtraCourse(@PathVariable String course_code,@PathVariable String teacher_ID,@RequestBody String[] studentIDs){

        if (teacherService.assignExtraCourse(studentIDs,course_code,teacher_ID).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("List empty"));
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.assignExtraCourse(studentIDs,course_code,teacher_ID));
}
}
