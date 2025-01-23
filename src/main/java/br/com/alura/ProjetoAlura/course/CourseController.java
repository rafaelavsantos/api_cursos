package br.com.alura.ProjetoAlura.course;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.ProjetoAlura.util.ErrorItemDTO;
import jakarta.validation.Valid;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    @PostMapping("/course/new")
    public ResponseEntity<?> createCourse(@Valid @RequestBody NewCourseDTO newCourse) {
        if(courseRepository.existsByCode(newCourse.getCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorItemDTO("code", "Course already registered in the system"));
        }

        Course course = newCourse.toModel();
        courseRepository.save(course);
        
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    @PutMapping("/course/{code}/inactive")
    public ResponseEntity<?> inactivateCourse(@PathVariable("code") String courseCode) {
        Course course = courseRepository.findByCode(courseCode);

        if(courseCode == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course with code " + courseCode + " not found.");
        }

        if(course.getStatus() == Status.INACTIVE) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course is already inactive.");
        }

        course.setStatus(Status.INACTIVE);
        course.setInactivedDate(LocalDate.now());
        courseRepository.save(course);

        return ResponseEntity.ok("Course " + courseCode + " was successfully deactivated.");
    }

    @GetMapping("/course/all")
    public List<CourseListItemDTO> listAllCourses() {
        return courseRepository.findAll().stream().map(CourseListItemDTO::new).toList();
    }

}
