package br.com.alura.ProjetoAlura.course;

import java.io.Serializable;
import java.time.LocalDate;

public class CourseListItemDTO implements Serializable {

    private String name;
    private String code;
    private String instructorEmail;
    private String description;
    private Status status;
    private LocalDate inactivedDate;

    public CourseListItemDTO(Course course) {
        this.name = course.getName();
        this.code = course.getCode();
        this.instructorEmail = course.getInstructorEmail();
        this.description = course.getDescription();
        this.status = course.getStatus();
        this.inactivedDate = course.getInactivedDate();
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

}
