package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
