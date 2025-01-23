package br.com.alura.ProjetoAlura.registration;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.ProjetoAlura.course.Course;
import br.com.alura.ProjetoAlura.course.CourseRepository;
import br.com.alura.ProjetoAlura.course.Status;
import br.com.alura.ProjetoAlura.user.User;
import br.com.alura.ProjetoAlura.user.UserRepository;

@RestController
public class RegistrationController {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public RegistrationController(RegistrationRepository registrationRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }
    
    @Transactional
    @PostMapping("/registration/new")
    public ResponseEntity<?> registrationStudent(@RequestParam Long userId, @RequestParam String courseCode) {
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        User user = userOptional.get();
        Course course = courseRepository.findByCode(courseCode);
        
        if(course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found.");
        }

        if(course.getStatus() != Status.ACTIVE) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("It is not possible to register in an inactive course.");
        }

        if(registrationRepository.existsByUserAndCourse(user, course)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already registrated in this course.");
        }

        Registration registration = new Registration(user, course);
        registrationRepository.save(registration);

        return ResponseEntity.status(HttpStatus.CREATED).body("Registration completed successfully");
    }

    @GetMapping("/registration/report")
    public ResponseEntity<List<RegistrationReportItem>> report() {
        List<RegistrationReportItem> items = registrationRepository.getCourseWithMostRegistrations();


        // // TODO: Implementar a Questão 4 - Relatório de Cursos Mais Acessados aqui...

        // // Dados fictícios abaixo que devem ser substituídos
        // items.add(new RegistrationReportItem(
        //         "Java para Iniciantes",
        //         "java",
        //         "Charles",
        //         "charles@alura.com.br",
        //         10L
        // ));

        // items.add(new RegistrationReportItem(
        //         "Spring para Iniciantes",
        //         "spring",
        //         "Charles",
        //         "charles@alura.com.br",
        //         9L
        // ));

        items.add(new RegistrationReportItem(
                "Maven para Avançados",
                "maven",
                "Charles",
                "charles@alura.com.br",
                9L
        ));

        return ResponseEntity.ok(items);
    }

}
