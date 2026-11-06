package com.spring.hibernate_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Student;

import java.util.List;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	@Bean 
	CommandLineRunner CommandLineRunner(StudentDao studentDao) {
		return runner -> {
			// System.out.println("Hello World");
			createStudent(studentDao);
			// readStudent(studentDao);
			// queryStudents(studentDao);
			// queryStudentLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudent(studentDao);
		};
	}

	private void createStudent(StudentDao studentDao){
		System.out.println("Creating student information...");
		Student student = new Student("ChieSan", 
		"Mapulak", "ritchie.mapulak@example.com");
		
		System.out.println("Saving student information...");
		studentDao.save(student);

		System.out.println("Saved! Generated id: " + student.getId());
	}

	private void readStudent(StudentDao studentDao){
		System.out.println("Retrieving student id 2...");
		Student student = studentDao.findbyId(3);

		System.out.println("Found the student " + student);
	}

	private void queryStudents(StudentDao studentDao){
		List <Student> students = studentDao.findAll();

		for(Student s: students){
			System.out.println(s);
		}
	}

	private void queryStudentLastName(StudentDao studentDao){
		List <Student> students = studentDao.findByLastName("Mapulak");

		for(Student s: students){
			System.out.println(s);
		}
	}

	private void updateStudent(StudentDao studentDao){
		int id= 2;
		System.out.println("Student id: " + id);
		Student student = studentDao.findbyId(id);

		System.out.println("Updating student name...");
		student.setFirstName("Erl");

		studentDao.update(student);

		System.out.println("Updated student information: " + student);
	}

	private void deleteStudent(StudentDao studentDao) {
		int id=3;
		System.out.println("Delete ID: " + id);

		studentDao.delete(id);
	}

	private void deleteAllStudent(StudentDao studentDao){
		System.out.println("Deleting all student information...");
		int rowsDeleted = studentDao.deleteAll();
		System.out.println("Number of deleted rows: "+ rowsDeleted);
	}
}
