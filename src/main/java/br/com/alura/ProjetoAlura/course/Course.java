package br.com.alura.ProjetoAlura.course;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();
    private String name;

    @Length(min = 4, max = 10)
    private String code;

    private String instructorEmail;;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate inactivedDate;

    @Deprecated
    public Course() {
    }

    public Course(String name, String code, String instructorEmail, String description, Status status, LocalDate inactivedDate) {
        this.name = name;
        this.code = code;
        this.instructorEmail = instructorEmail;
        this.description = description;
        this.status = status;
        this.inactivedDate = inactivedDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getInactivedDate() {
        return inactivedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setInactivedDate(LocalDate inactivedDate) {
        this.inactivedDate = inactivedDate;
    }
}
