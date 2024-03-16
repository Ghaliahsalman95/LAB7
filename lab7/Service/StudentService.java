package com.example.lab7.Service;

import com.example.lab7.Model.Course;
import com.example.lab7.Model.Student;
import com.example.lab7.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean updateStudent(String id, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equalsIgnoreCase(id)) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    //---------------------------------------
    public boolean deleteStudent(String id) {
        for (Student student : students) {
            if (student.getID().equalsIgnoreCase(id)) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    //-------------------First endpoint from 4logics-------------------
    public ArrayList<Student> getTopCourse(String course) {
        ArrayList<Student> topTenList = new ArrayList<>();
        for (Student student : students) {
            for (Course courseStudent : student.getCourses()) {
                if (courseStudent.getCode().equalsIgnoreCase(course)) {
                    if (courseStudent.getScore() >= 90) {
                        topTenList.add(student);
                    }
                }
            }
        }
        return topTenList;
    }

    //----------------------Second endpoint from 4logics-
    public ArrayList<Student> getFaild(String course) {
        ArrayList<Student> faildList = new ArrayList<>();
        double hour=0;
        for (Student student : students) {
            for (Course courseStudent : student.getCourses()) {
                if (courseStudent.getCode().equalsIgnoreCase(course)) {
                    hour=courseStudent.getHours();
                    if (courseStudent.getScore() <= 60) {

                        faildList.add(student);
                        ArrayList<Student>studentsFail=noabsencehours(course,hour);
                        faildList.addAll(studentsFail);
                    }
                }
            }
        }
        return faildList;
    }
    //------------------Third endpoint from 4logics

    public ArrayList<Student> studyOnline_Inperson(String courseCode,String kind) {

        ArrayList<Student> studyOnline = new ArrayList<>();
        for (Student student : students) {
            for (Course courseStudent : student.getCourses()) {
                if (courseStudent.getCode().equalsIgnoreCase(courseCode)) {
                    if (courseStudent.getKind().equalsIgnoreCase(kind)) {
                        studyOnline.add(student);
                    }
                }
            }
        }
        return studyOnline;
    }
///             4 logic
    public ArrayList<Student> noabsencehours(String coursecode,double hours) {
        ArrayList<Student> absenceList = new ArrayList<>();
        for (Student student : students) {
            for (Course courseStudent : student.getCourses()) {
                if (courseStudent.getCode().equalsIgnoreCase(coursecode)) {
                    double total=hours*15;//15week
                    total*=0.25;//multiply by 25%//allow absence hour for course
                    double totalHour=courseStudent.getAbsence()*15;
                    totalHour*=totalHour*0.25;
                    if (totalHour>total){
                        absenceList.add(student);

                    }

                }
            }
        }


        return absenceList;
    }


}