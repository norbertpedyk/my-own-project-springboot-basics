package pl.pedyk.java.spring.myownprojectspringbootbasics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pedyk.java.spring.myownprojectspringbootbasics.dao.StudentRepository;
import pl.pedyk.java.spring.myownprojectspringbootbasics.model.Student;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> startListOfStudents() {
        return studentRepository.findAll();
//                List.of(
//                new Student("Jan Nowak", 1L, 26, LocalDate.of(1995, Month.SEPTEMBER, 23), "jan.nowak@gmail.com"),
//                new Student("Katarzyna Bak", 2L, 30, LocalDate.of(1991, Month.OCTOBER, 20), "kasia.bak@gmail.com"),
//                new Student("Krzysztof Krawczyk", 3L, 31, LocalDate.of(1990, Month.JANUARY, 2), "krzysztof.k@gmail.com"),
//                new Student("Ada Chodakowska", 4L, 32, LocalDate.of(1981, Month.MAY, 9), "chodakowska@gmail.com")
//                  );
    }

    public void registerNewStudent(Student student) {
        if (studentRepository.findStudentByEmail(student.getEmail()).isEmpty()) {
            studentRepository.save(student);
        } else {
            throw new IllegalStateException("emial taken ");
        }
    }

    public void deleteNewStudent(Long id) {
        Optional<Student> studentById = studentRepository.findById(id);
        if (studentById.isPresent()) {
            studentRepository.delete(studentById.get());
        } else {
            throw new IllegalStateException("there is no Student with that id ");
        }
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("There is no Student with that id "));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
            if (optionalStudent.isPresent()) {
                throw new IllegalStateException("email taken");
            } else {
                student.setEmail(email);
            }

        }
    }
}
