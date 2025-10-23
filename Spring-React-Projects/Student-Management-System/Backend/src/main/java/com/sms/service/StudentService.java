package com.sms.service;

import com.sms.model.Student;
import com.sms.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student addStudent(Student student){
        student.setId(UUID.randomUUID().toString().split("-")[0]);
       return studentRepo.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student getStudentById(String id){
       return studentRepo.findById(id).get();
    }
    public Student updateStudent(Student studentRequest){
        Student existingStudent=studentRepo.findById(studentRequest.getId()).get();
        existingStudent.setName(studentRequest.getName());
        existingStudent.setEmail(studentRequest.getEmail());
       return  studentRepo.save(existingStudent);
    }

    public String deleteStudent(String studentId){
        studentRepo.deleteById(studentId);
        return "Student Deleted with ID "+studentId;
    }
}
