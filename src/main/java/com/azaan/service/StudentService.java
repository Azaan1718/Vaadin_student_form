package com.azaan.service;

import org.springframework.stereotype.Service;

import com.azaan.entity.Student;
import com.azaan.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository=repository;
    }

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

}