package com.mariomanhique.demo.gradle.kotlin.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void saveStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email already registered in database");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("Not found");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(
            Long studentId,
            String name,
            String email
    ){

        //Because of @Transactional, the student goes to a manage state,
        // and we can update Student by using the setters methods

        Student student = studentRepository.findById(studentId)
                        .orElseThrow(()-> new IllegalStateException(
                                "Student with id" +studentId+ "does not exist"
                        ));

        if(name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)){
            student.setName(name);
        }


        if(email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
