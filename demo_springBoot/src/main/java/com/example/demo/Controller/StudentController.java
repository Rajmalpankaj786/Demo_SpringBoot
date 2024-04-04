package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Student;
import com.example.demo.Service.studentService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

	@Autowired
	private studentService studentService;
	
	@PostMapping
	public ResponseEntity<Student> postStudent(@RequestBody Student st){
		return new ResponseEntity<Student>(studentService.postStudent(st),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudent(){
		return new ResponseEntity<List<Student>>(studentService.getAllStudent() , HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer id ,@RequestBody Student st){
		return new ResponseEntity<Student>(studentService.updateStudent(id, st), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable Integer id){
		return new ResponseEntity<Student>(studentService.deleteStudent(id) ,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{pageNo}/{recordPerPage}")
	public ResponseEntity<List<Student>> pageRequest(@PathVariable Integer pageNo ,@PathVariable Integer recordPerPage){
		return new ResponseEntity<List<Student>>(studentService.pageWiseStudentEntity(pageNo, recordPerPage) ,HttpStatus.OK);
	}
	
	
	
}
