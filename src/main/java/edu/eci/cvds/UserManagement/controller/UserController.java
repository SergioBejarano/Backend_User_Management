package edu.eci.cvds.UserManagement.controller;

import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.model.Student;
import edu.eci.cvds.UserManagement.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint to retrieve a paginated list of students.
     *
     * @param pageNumber The page number to retrieve (1-based).
     * @param pageSize The number of students to return per page.
     * @return A list of students for the specified page.
     */
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        List<Student> students = userService.getStudents(pageNumber, pageSize);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /**
     * Endpoint to get the total count of students.
     *
     * @return The total number of students wrapped in a ResponseEntity.
     */
    @GetMapping("/students/count")
    public ResponseEntity<Long> getTotalStudentCount() {
        long totalStudents = userService.getTotalStudentCount();
        return ResponseEntity.ok(totalStudents);
    }


    /**
     * Endpoint to update the course of a student.
     *
     * @param studentId The ID of the student to update (String).
     * @param course    The new course.
     * @return A response indicating success or failure.
     */
    @PutMapping("/students/{id}/course")
    public ResponseEntity<Void> updateStudentCourse(
            @PathVariable("id") String studentId,
            @RequestParam String course) {
        userService.updateStudentCourse(studentId, course);
        return ResponseEntity.noContent().build();
    }


    /**
     * Endpoint to update the contact information of a responsible.
     *
     * @param docNumber   The document number of the responsible.
     * @param email       The new email address, if updating.
     * @param phoneNumber The new phone number, if updating.
     * @return A response indicating success or failure.
     */
    @PutMapping("/responsibles/{docNumber}/contact")
    public ResponseEntity<Void> updateResponsibleContactInfo(
            @PathVariable("docNumber") String docNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber) {
        userService.updateResponsibleContactInfo(docNumber, email, phoneNumber);
        return ResponseEntity.noContent().build();
    }


    /**
     * Retrieves a paginated list of all responsibles.
     *
     * @param pageNumber The page number to retrieve (1-based).
     * @param pageSize   The number of responsibles to return per page.
     * @return A ResponseEntity containing a list of responsibles.
     */
    @GetMapping("/responsibles")
    public ResponseEntity<ArrayList<Responsible>> getAllResponsibles(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        try {
            ArrayList<Responsible> responsibles = userService.getAllResponsibles(pageNumber, pageSize);
            return ResponseEntity.ok(responsibles);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Eliminate an economic responsible for its ID.
     *
     * @param document The number of document of the economic responsible to delete.
     * @return ResponseEntity with the status of the operation.
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteResponsible(@PathVariable String document) {
        try {
            userService.deleteByDocument(document);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Economic responsible successfully eliminated");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Economic responsible not found");
        }
    }

}