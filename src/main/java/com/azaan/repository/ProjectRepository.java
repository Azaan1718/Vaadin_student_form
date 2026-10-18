package com.azaan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.azaan.entity.Project;
import com.azaan.entity.User;

public interface ProjectRepository extends JpaRepository<Project, Long> {

     List<Project> findByStudent(User student);


}