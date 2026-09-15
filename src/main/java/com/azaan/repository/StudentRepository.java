package com.azaan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azaan.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}