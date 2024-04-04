package com.example.demo.Repositary;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
	
	public Student findByName(String name);

}
