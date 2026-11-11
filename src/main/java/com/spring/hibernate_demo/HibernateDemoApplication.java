package com.spring.hibernate_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_demo.dao.AppDao;
import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Review;
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
     * Sample Implementation
     */
	// CommandLineRunner CommandLineRunner(StudentDao studentDao) {
	// 	return runner -> {
			// System.out.println("Hello World");
	// };

	/**
     * Runner dedicated to basic CRUD and 
	 * query operations for Student entities.
     */
	// CommandLineRunner CommandLineRunner(StudentDao studentDao) {
	// 	return runner -> {
			// 
			// createStudent(studentDao);
			// readStudent(studentDao);
			// queryStudents(studentDao);
			// queryStudentLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudent(studentDao);
	// };

	/**
     * Runner for testing Hibernate JPA mapping operations 
	 * (Courses, Instructors, Students).
     * Uncomment individual methods inside 
	 * to execute specific DAO test cases.
     */
	@Bean 
	CommandLineRunner CommandLineRunner(AppDao app) {
		return runner -> {
			createInstructor(app);
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
			// deleteCourseOnly(app);
			// createCourseAndReviews(app);
			// retrieveCourseandReviews(app);
			// deleteCourseAndReviews(app);
			// createCourseAndStudents(app);
			// findCourseAndStudents(app);
			// findStudentAndCourses(app);
			// addMoreCoursesForStudent(app);
			// deleteCourseById(app);
			// deleteStudent(app);
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

	/**
	 * Creates an instructor with details and saves both.
	 *
	 * @param app the DAO for database operations
	 */
	private void createInstructor(AppDao app){
		Instructor ins = new Instructor("Rylee", 
						"Erazo", "rylee.erazo@example.com");
		InstructorDetail deets = new InstructorDetail("Nihongo", 
						"Solving sentence patterns");

		ins.setInstructorDeets(deets);

		System.out.println("Information Saved!");
		app.save(ins);
	}

	/**
	 * Finds an instructor by id and prints it with its details.
	 *
	 * @param app the DAO for database operations
	 */
	private void findInstructor(AppDao app) {
		int id = 1; 
		System.out.println("Find id: " + id);

		Instructor ins= app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Details: " + ins.getInstructorDeets());
	}

	/**
	 * Deletes an instructor by id, with its details (cascade).
	 *
	 * @param app the DAO for database operations
	 */
	private void deleteInstructor(AppDao app) {
		int id = 23;
		System.out.println("Delete id: " + id);

		app.deleteInstructorId(id);
		System.out.println("Deleted successfully!");
	}

	/**
	 * Finds instructor details by id and prints the linked
	 * instructor.
	 *
	 * @param app the DAO for database operations
	 */
	private void findInstructorDetail(AppDao app) {
		int id = 3;
		InstructorDetail inDeets = app.findInstructorDetailById(id);

		System.out.println("Details: " + inDeets);

		System.out.println("Instructor: " + inDeets.getInstructor());
	}

	/**
	 * Deletes instructor details by id, keeping the instructor.
	 *
	 * @param app the DAO for database operations
	 */
	private void deleteInstructorDetail(AppDao app){
		int id = 6;
		System.out.println("Delete id: " + id);

		app.deleteInstructorDetail(id);
		System.out.println("Deleted Successfully!");
	}

	/**
	 * Creates an instructor with details and two courses, then
	 * saves them together.
	 *
	 * @param app the DAO for database operations
	 */
	private void createInstructorWithCourses(AppDao app){
		Instructor ins = new Instructor("Peri", 
						"Winkle", "peri.winkle@gmail.com");

		InstructorDetail deets = new InstructorDetail("Science",
								 "Sprinkling Dust");

		ins.setInstructorDeets(deets);

		Course course1 = new Course("Physics");
		Course course2 = new Course("Biology");

		ins.add(course1);
		ins.add(course2);

		System.out.println("Courses saved: " + ins.getCourses());
		app.save(ins);
	}

	/**
	 * Finds an instructor by id and prints their courses.
	 *
	 * @param app the DAO for database operations
	 */
	private void findInstructorWithCourses(AppDao app) {
		int id = 8;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor; " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	/**
	 * Finds courses by instructor id and sets them on the
	 * instructor.
	 *
	 * @param app the DAO for database operations
	 */
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

	/**
	 * Finds an instructor and courses in one query (JOIN FETCH).
	 *
	 * @param app the DAO for database operations
	 */
	private void findInstructorWithCoursesJoinFetch(AppDao app) {
		int id = 8;

		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	/**
	 * Updates the last name of an instructor by id.
	 *
	 * @param app the DAO for database operations
	 */
	private void updateInstructor(AppDao app) {
		int id = 5;

		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		ins.setLastName("Cosma");
		System.out.println("Updated!");
		app.update(ins);
	}

	/**
	 * Deletes an instructor by id, keeping their courses.
	 *
	 * @param app the DAO for database operations
	 */
	private void deleteInstructorOnly(AppDao app) {
		int id= 10;
		System.out.println("Delete instructor: " +id);

		app.deleteInstructorById(id);
		System.out.println("Deleted successfully!");
	}

	/**
	 * Deletes a course by id, keeping its instructor.
	 *
	 * @param app the DAO for database operations
	 */
	private void deleteCourseOnly(AppDao app) {
		int id= 22;

		app.deleteCourseById(id);
		System.out.println("Course id deleted: " + id);
	}

	/**
	 * Creates a course with three reviews and saves them.
	 *
	 * @param app the DAO for database operations
	 */
	private void createCourseAndReviews(AppDao app) {
		Course course = new Course("How to Speed Run Spring in 1 Day?");

		course.addReview(new Review("Great speed 2x... loved it!!!"));
		course.addReview(new Review("I wanted the speed 0.75x :(("));
		course.addReview(new Review("Hoping to do a speed of 3x :D."+
							        "Anyways, nice!"));

		System.out.println("Course saved!");
		app.save(course);
	}

	/**
	 * Finds a course by id and prints it with its reviews.
	 *
	 * @param app the DAO for database operations
	 */
	private void retrieveCourseandReviews(AppDao app) {
		int id = 25;

		System.out.println("Get course and reviews from id: " + id);

		Course course = app.findCourseAndReviewsByCourseId(id);

		System.out.println(course);

		System.out.println(course.getReview());
	}

	/**
	 * Deletes a course by id, with its reviews (cascade).
	 *
	 * @param app the DAO for database operations
	 */
	private void deleteCourseAndReviews(AppDao app) {
		int id = 25;
		System.out.println("Delete course: " + id);

		app.deleteCourseById(id);
		System.out.println("Deleted successfully");
	}

	/**
	 * Creates a course with two students and saves them.
	 *
	 * @param app the DAO for database operations
	 */
	private void createCourseAndStudents(AppDao app) {
		Course course = new Course("How To Be A FullStack Dev In Half A Day");

		Student student1 = new Student("Light",
						  "Yagami", "lightyagami@gmail.com");
		Student student2 = new Student("Dark", 
		                  "Continent", "dcontinent@gmail.com");

		course.addStudent(student1);
		course.addStudent(student2);

		app.save(course);
		System.out.println("Successfully enrolled!");
	}

	/**
	 * Finds a course by id and prints its enrolled students.
	 *
	 * @param app the DAO for database operations
	 */
	private void findCourseAndStudents(AppDao app) {
		int id = 26;

		Course course = app.findCourseAndStudentByCourseId(id);

		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());
	}

	/**
	 * Finds a student by id and prints their courses.
	 *
	 * @param app the DAO for database operations
	 */
	private void findStudentAndCourses(AppDao app) {
		int id = 1;
		Student student = app.findStudentAndCoursesByStudentId(id);

		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());
	}

	/**
	 * Enrolls an existing student in three new courses.
	 *
	 * @param app the DAO for database operations
	 */
	private void addMoreCoursesForStudent(AppDao app) {
		int id = 21;

		Student student = app.findStudentAndCoursesByStudentId(id);

		Course course1 = new Course("How To Test without"+
									" Running Applications");
		Course course2 = new Course("How To Develop without Computer");
		Course course3 = new Course("How To Be a Legit Developer");

		student.addCourse(course1);
		student.addCourse(course2);
		student.addCourse(course3);

		System.out.println("Successfully enrolled to new courses!");

		app.update(student);
	}

	/**
	 * Deletes a course by id.
	 *
	 * @param app the DAO for database operations
	 */
	private void deleteCourseById(AppDao app) {
		int id = 28;

		System.out.println("Delete course: " +id);
		app.deleteCourseById(id);
		System.out.println("Deleted Successfully");
	}

	/**
	 * Deletes a student by id and clears their course links.
	 *
	 * @param app the DAO for database operations
	 */
	private void deleteStudent(AppDao app) {
		int id = 22;
		System.out.println("Delete student: " + id);

		app.deleteStudentById(id);
		System.out.println("Deleted Successfully!");
	}
}
