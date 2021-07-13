package pl.pedyk.java.spring.myownprojectspringbootbasics.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pedyk.java.spring.myownprojectspringbootbasics.model.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")  //this is JPQL
    Optional<Student> findStudentByEmail(String email);


}
