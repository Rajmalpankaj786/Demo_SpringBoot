package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Student;
import com.example.demo.Exception.NotFoundException;
import com.example.demo.Repositary.StudentRepo;

@Service
public class studentService {

	@Autowired
	private StudentRepo studentRepo;
	
	public Student postStudent(Student st) {
		Student st1 = studentRepo.findByName(st.getName());
		if(st1 != null) {
			throw new NotFoundException("user already exists this name: " +st.getName());
		}
		return studentRepo.save(st);
	}
	
	public List<Student> getAllStudent(){
		return studentRepo.findAll();
	}
	
	public Student updateStudent(Integer id,Student student ) {
		Student st = studentRepo.findById(id).get();
		if(st.getId()==null) {
			throw new NotFoundException("student does not exist given id : "+id);
		}
		
		st.setName(student.getName());
		return studentRepo.save(st);
		
	}
	
	public Student deleteStudent(Integer id) {
		Optional<Student> st = studentRepo.findById(id);
		if(st.isPresent()) {
			Student student = st.get();
		    studentRepo.deleteById(id);
			return student;
		}
		throw new NotFoundException("student does not exist given id : "+id);
	}
	
	public List<Student> pageWiseStudentEntity(Integer pageNo , Integer recordPerPage) {
		Pageable pageable = PageRequest.of(pageNo, recordPerPage);
		Page<Student> page = studentRepo.findAll(pageable);
		
		if(page.hasContent()) {
			return page.getContent();
		}
		
		throw new NotFoundException("No Records for the current page : " +pageNo);
	}
}
