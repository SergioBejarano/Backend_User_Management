package edu.eci.cvds.UserManagement.model;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Student class represents a student entity, including information about
 * their academic course, year, responsible person, and the relationship with the responsible person.
 */

@Entity
@Table(name = "students", schema = "public")
public class Student extends User{
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    private String name;
    private String document;
    @Column(name = "document_type")
    private String documentType;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "responsible_document")
    private String responsibleDocument;

    public Student (String id, String name,String document, String documentType, String courseName, String responsibleDocument){
        this.id=id;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(String.valueOf(id));
        setUserName(name);
        setPassword(encodedPassword);
        this.name=name;
        this.document = document;
        this.documentType = documentType;
        this.courseName = courseName;
        this.responsibleDocument = responsibleDocument;
        setRole("student");
    }

    public Student() {
        super();
    }

    public String getUserName() {

        return super.getUserName();
    }

    public String getResponsibleDocument() {
        return responsibleDocument;
    }

    public String getDocument() {
        return document;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getPassword() {
        return super.getPassword();
    }

    /**
     * Sets the course of the student.
     *
     * @param courseName the new course name.
     */
    public void setCourse(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Gets the name of the student.
     *
     * @return the student's name.
     */
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }
}
