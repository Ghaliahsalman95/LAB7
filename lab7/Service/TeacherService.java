package com.example.lab7.Service;

import com.example.lab7.Model.Course;
import com.example.lab7.Model.Student;
import com.example.lab7.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {


    private CourseService courseService;
    private StudentService studentService;
    ArrayList<Teacher> teachers = new ArrayList<>();

    public ArrayList<Teacher> get_Teachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public boolean updateTeacher(String id, Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getID().equalsIgnoreCase(id)) {
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    //---------------------------------------
    public boolean deleteTeacher(String id) {
        for (Teacher teacher : teachers) {
            if (teacher.getID().equalsIgnoreCase(id)) {
                teachers.remove(teacher);
                return true;
            }
        }
        return false;
    }

    //--------------------------------1-

    public ArrayList<Teacher> getTeachInPerson_Online(String courseCode, String kind) {
        ArrayList<Teacher> teachersList = new ArrayList<>();
        for (Teacher teacher : teachers) {
            if (teacher.getCourse().getCode().equalsIgnoreCase(courseCode)) {
                if (teacher.getCourse().getKind().equalsIgnoreCase(kind)) {
                    teachersList.add(teacher);
                }
            }
        }
        return teachersList;

    }

    //-----------------------2-
    public ArrayList<Student> getlistTearcherStudent(String courseCode, String teacherID) {
        ArrayList<Student> studentsList = new ArrayList<>();
        for (Student student : studentService.getStudents()) {
            for (Course course : student.getCourses()) {
                if (course.getCode().equalsIgnoreCase(courseCode)) {
                    for (Teacher teacher : teachers) {
                        if (teacher.getID().equalsIgnoreCase(teacherID)) {
                            studentsList.add(student);
                        }
                    }
                }
            }
        }
        return studentsList;

    }

    //------------------------3-
    public ArrayList<Student> getstudentExtracourse(String courseCode, String teacherID) {
        ArrayList<Student> teachers1List = new ArrayList<>();
        for (Student student : studentService.getStudents()) {
            for (Course course : student.getCourses()) {
                if (course.getCode().equalsIgnoreCase(courseCode)) {
                    for (Teacher teacher : teachers) {
                        if (teacher.getID().equalsIgnoreCase(teacherID)) {
                            if (course.isExtraCourse()) {
                                teachers1List.add(student);
                            }
                        }
                    }
                }
            }
        }
        return teachers1List;
    }


    //-------------------------4
    public ArrayList<Student> assignExtraCourse(String[] studentIDs, String courseCode, String teacherID) {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        for (Student student : studentService.getStudents()) {
            for (String id : studentIDs) {
                if (student.getID().equalsIgnoreCase(id)) {
                    for (Course course : student.getCourses()) {
                        if (course.getCode().equalsIgnoreCase(courseCode)) {
                            for (Teacher teacher : teachers) {
                                if (teacher.getID().equalsIgnoreCase(teacherID)) {
                                    if (teacher.getCourse().getCode().equalsIgnoreCase(courseCode)) {
                                        course.setExtraCourse(true);
                                        studentArrayList.add(student);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return studentArrayList;
    }


}












