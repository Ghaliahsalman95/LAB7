    package com.example.lab7.Service;

    import com.example.lab7.Model.Course;
    import com.example.lab7.Model.Student;
    import com.example.lab7.Model.Teacher;
    import org.springframework.boot.autoconfigure.web.WebProperties;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;

    @Service
    public class CourseService {

        ArrayList<Course> courses = new ArrayList<>();
        private StudentService studentService;
        private TeacherService teacherService;


        public ArrayList<Course> getCourses() {
            return courses;
        }

        public void addCourse(Course course) {
            courses.add(course);
        }

        public boolean updateCourse(String course_Code, Course course) {
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getCode().equalsIgnoreCase(course_Code)) {
                    courses.set(i, course);
                    return true;
                }
            }
            return false;
        }

        //---------------------------------------
        public boolean deleteCourse(String course_Code) {
            for (Course course : courses) {
                if (course.getCode().equalsIgnoreCase(course_Code)) {
                    courses.remove(course);
                    return true;
                }
            }
            return false;
        }

        //---------------------------1- ---------------------
        public ArrayList<Student> whoAddCourse(String course) {
            ArrayList<Student> students = new ArrayList<>();
            for (Student student : studentService.getStudents()) {
                for (Course course1 : student.getCourses()) {
                    if (course1.getCode().equalsIgnoreCase(course)) {
                        students.add(student);

                    }


                }
            }
            return students;
        }

        //------------------2- ------------------------
        public ArrayList<Student> whoDelete(String course_Code) {
            ArrayList<Student> deletecourseList = new ArrayList<>();
            for (Student student : studentService.getStudents()) {
                for (String courseCode : student.getCodeCourse()) {//array String in model student named courseCode
                    if (courseCode.equalsIgnoreCase(course_Code)) {
                        deletecourseList.add(student);
                    }
                }
            }
            return deletecourseList;
        }

        //--------------------3---------------------
        public ArrayList<Teacher> noasignTeacher(String courseCode) {
            ArrayList<Teacher> noasignTeacher = new ArrayList<>();
            for (Teacher teacher : teacherService.get_Teachers()) {

                if (!teacher.getCourse().getCode().equalsIgnoreCase(courseCode)) {
                    noasignTeacher.add(teacher);
                }
            }

            return noasignTeacher;
        }

        //----------------------4--------------------
        public ArrayList<Teacher> whoTeach(String courseCode) {
            ArrayList<Teacher> teachCourse = new ArrayList<>();
            for (Teacher teacher : teacherService.get_Teachers()) {
                if (teacher.getCourse().getCode().equalsIgnoreCase(courseCode)) {
                    teachCourse.add(teacher);
                }
            }
            return teachCourse;
        }


    }



