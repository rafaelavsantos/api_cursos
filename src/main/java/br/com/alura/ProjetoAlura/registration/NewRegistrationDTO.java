package br.com.alura.ProjetoAlura.registration;

import br.com.alura.ProjetoAlura.course.Course;
import br.com.alura.ProjetoAlura.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NewRegistrationDTO {

    @NotBlank
    @NotNull
    private Course course;

    @NotBlank
    @NotNull
    private User user;

    public NewRegistrationDTO() {}

    public NewRegistrationDTO(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
