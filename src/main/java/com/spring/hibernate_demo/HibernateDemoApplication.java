package com.spring.hibernate_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_demo.dao.AppDao;
import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;

import java.util.List;
import org.springframework.boot.CommandLineRunner;

/**
 * Main application class for the Spring Boot Hibernate demonstration.
 * Serves as the entry point for the application 
 * and executes CRUD operations 
 */
@SpringBootApplication
public class HibernateDemoApplication {

	/**
     * Main entry point of the Spring Boot application.
     */
	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	/**
     * Configures a {@link CommandLineRunner} bean that executes 
	 * code after the application context is loaded.
     *
     * @param studentDao the Data Access Object 
	 * used for performing student database operations
     * @return a {@link CommandLineRunner} callback lambda
     */
	@Bean 
	// CommandLineRunner CommandLineRunner(StudentDao studentDao) {
	// 	return runner -> {

	CommandLineRunner CommandLineRunner(AppDao app) {
		return runner -> {
			// System.out.println("Hello World");

			// createStudent(studentDao);
			// readStudent(studentDao);
			// queryStudents(studentDao);
			// queryStudentLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudent(studentDao);

			// createInstructor(app);
			// findInstructor(app);
			// deleteInstructor(app);
			// findInstructorDetail(app);
			// deleteInstructorDetail(app);
			// createInstructorWithCourses(app);
			// findInstructorWithCourses(app);
			// findCoursesForInstructor(app);
			// findInstructorWithCoursesJoinFetch(app);
			// updateInstructor(app);
			// deleteInstructorOnly(app);
			deleteCourseOnly(app);
		};
	}

	/**
     * Creates and persists a new student entity to the database.
     *
     * @param studentDao the Data Access Object for database operations
     */
	private void createStudent(StudentDao studentDao){
		System.out.println("Creating student information...");
		Student student = new Student("Rylee", 
		"Erazo", "rylee.erazo@example.com");
		
		System.out.println("Saving student information...");
		studentDao.save(student);

		System.out.println("Saved! Generated id: " + student.getId());
	}

	/**
     * Retrieves a student record from the database by ID 
	 * and displays it.
     *
     * @param studentDao the Data Access Object for database operations
     */
	private void readStudent(StudentDao studentDao){
		System.out.println("Retrieving student id 2...");
		Student student = studentDao.findbyId(3);

		System.out.println("Found the student " + student);
	}

	/**
     * Fetches all student records from the database and 
	 * prints them to the console.
     *
     * @param studentDao the Data Access Object for 
	 * database operations
     */
	private void queryStudents(StudentDao studentDao){
		List <Student> students = studentDao.findAll();

		for(Student s: students){
			System.out.println(s);
		}
	}

	/**
     * Queries and displays all students matching a specific last name.
     *
     * @param studentDao the Data Access Object for database operations
     */
	private void queryStudentLastName(StudentDao studentDao){
		List <Student> students = studentDao.findByLastName
		("Mapulak");

		for(Student s: students){
			System.out.println(s);
		}
	}

	/**
     * Updates the first name of a student record by ID 
	 * and saves the changes.
     *
     * @param studentDao the Data Access Object for database operations
     */
	private void updateStudent(StudentDao studentDao){
		int id= 2;
		System.out.println("Student id: " + id);
		Student student = studentDao.findbyId(id);

		System.out.println("Updating student name...");
		student.setFirstName("Erl");

		studentDao.update(student);

		System.out.println("Updated student information: " + student);
	}

	/**
     * Deletes a student record from the database by ID.
     *
     * @param studentDao the Data Access Object for database operations
     */	
	private void deleteStudent(StudentDao studentDao) {
		int id=3;
		System.out.println("Delete ID: " + id);

		studentDao.delete(id);
	}

	/**
     * Removes all student records from the database and 
	 * displays the number of deleted rows.
     *
     * @param studentDao the Data Access Object for database operations
     */
	private void deleteAllStudent(StudentDao studentDao){
		System.out.println("Deleting all student information...");
		int rowsDeleted = studentDao.deleteAll();
		System.out.println("Number of deleted rows: "+ rowsDeleted);
	}

	//Continuation
	private void createInstructor(AppDao app){
		
		Instructor ins = new Instructor("Rylee", "Erazo", "rylee.erazo@example.com");
		InstructorDetail deets = new InstructorDetail("Nihongo", "Solving sentence patterns");

		ins.setInstructorDeets(deets);
		
		System.out.println("Information Saved!");
		app.save(ins);
	}

	private void findInstructor(AppDao app) {
		int id = 1; 
		System.out.println("Find id: " + id);

		Instructor ins= app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Details: " + ins.getInstructorDeets());
	}

	private void deleteInstructor(AppDao app) {
		int id = 23;
		System.out.println("Delete id: " + id);

		app.deleteInstructorId(id);
		System.out.println("Deleted successfully!");
	}

	private void findInstructorDetail(AppDao app) {
		
		int id = 3;
		InstructorDetail inDeets = app.findInstructorDetailById(id);

		System.out.println("Details: " + inDeets);

		System.out.println("Instructor: " + inDeets.getInstructor());
	}

	private void deleteInstructorDetail(AppDao app){
		int id = 6;
		System.out.println("Delete id: " + id);

		app.deleteInstructorDetail(id);
		System.out.println("Deleted Successfully!");
	}

	private void createInstructorWithCourses(AppDao app){

		Instructor ins = new Instructor("Peri", "Winkle", "peri.winkle@gmail.com");

		InstructorDetail deets = new InstructorDetail("Science", "Sprinkling Dust");

		ins.setInstructorDeets(deets);

		Course course1 = new Course("Physics");
		Course course2 = new Course("Biology");

		ins.add(course1);
		ins.add(course2);

		System.out.println("Courses saved: " + ins.getCourses());
		app.save(ins);
	}

	private void findInstructorWithCourses(AppDao app) {
		int id = 8;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor; " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	private void findCoursesForInstructor(AppDao app) {
		int id = 8;

		System.out.println("Find instructor id: " + id);
		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses from id: " + id);
		List <Course> courses = app.findCoursesByInstructorId(id);

		ins.setCourses(courses);

		System.out.println(ins.getCourses());
	}

	private void findInstructorWithCoursesJoinFetch(AppDao app) {
		
		int id = 8;

		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}	

	private void updateInstructor(AppDao app) {
		int id = 5;

		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		ins.setLastName("Cosma");
		System.out.println("Updated!");
		app.update(ins);
	}

	private void deleteInstructorOnly(AppDao app) {
		int id= 9;
		System.out.println("Delete instructor: " +id);

		app.deleteInstructorById(id);
		System.out.println("Deleted successfully!");
	}	

	private void deleteCourseOnly(AppDao app) {
		int id= 6;

		app.deleteCourseById(id);
		System.out.println("Course id deleted: " + id);
	
	}
}
