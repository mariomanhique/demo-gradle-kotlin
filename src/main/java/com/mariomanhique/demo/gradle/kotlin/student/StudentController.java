package com.mariomanhique.demo.gradle.kotlin.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<Student> getStudents(){
       return studentService.getStudents();
    }

    @PostMapping("save_student")
    public void saveNewStudent(@RequestBody StudentRequest studentRequest){
        Student student = new Student();
        student.setName(studentRequest.name());
        student.setEmail(studentRequest.email());
        student.setDob(studentRequest.dob());
        student.setAge(studentRequest.age());

        studentService.saveStudent(student);
    }

    @DeleteMapping(path= "delete_student/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "update/{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        studentService.updateStudent(studentId,name,email);
    }

}
