package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repository.StudentRepo;
import com.example.entity.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin(origins = "http://localhost:3000") 
@RestController
public class StudentController {
@Autowired
StudentRepo repo;
@GetMapping("/students")
public List<Student>getAllStudents()
{
	List<Student>mystudentlist=repo.findAll();
	return mystudentlist;
	
}

@GetMapping("/students/{id}")
public Student getStudent(@PathVariable int id)
{
	Student studentobj=repo.findById(id).get();
	return studentobj;
	
}
@PostMapping("/student/add")
@ResponseStatus(code=HttpStatus.CREATED)
public void createstudent(@RequestBody Student student) {
    repo.save(student);
}

//@PutMapping("/student/update/{id}")
//public Student updateStudents(@PathVariable int id) {
//	Student studentobj=repo.findById(id).get();
//	studentobj.setName("updated_mouli");
//	studentobj.setPercentage(90);
//	repo.save(studentobj); 
//    return studentobj;
//}
@PutMapping("/student/update/{id}")
public Student updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
    return repo.findById(id).map(student -> {
        student.setName(updatedStudent.getName());
        student.setPercentage(updatedStudent.getPercentage());
        student.setBranch(updatedStudent.getBranch());
        return repo.save(student);
    }).orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
}

@DeleteMapping("/student/delete/{id}")
public void removeStudent(@PathVariable int id) {
	Student studentobj=repo.findById(id).get();
	repo.delete(studentobj); 
}

}
