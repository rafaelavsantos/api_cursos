package br.com.alura.ProjetoAlura.registration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.ProjetoAlura.course.Course;
import br.com.alura.ProjetoAlura.user.User;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    
    @Query(
        value= """
            SELECT
                c.id AS courseId, 
                c.name AS courseName, 
                u.name AS instructorName, 
                u.email AS instructorEmail,
                COUNT(r.id) AS totalRegistrations
            FROM
                registration r
            JOIN
                course c ON r.course = c.id
                user u ON c.instructor_id = u.id
            GROUP BY
                c.id, c.name, c.code. u.name, u.email
            ORDER BY
                COUNT(r.id) DESC
        """, nativeQuery=true
    )
    List<RegistrationReportItem> getCourseWithMostRegistrations();

    boolean existsByUserAndCourse(User user, Course course);

}
