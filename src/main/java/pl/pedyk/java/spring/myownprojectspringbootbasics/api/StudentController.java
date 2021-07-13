package pl.pedyk.java.spring.myownprojectspringbootbasics.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pedyk.java.spring.myownprojectspringbootbasics.model.Student;
import pl.pedyk.java.spring.myownprojectspringbootbasics.service.StudentService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> startListOfStudents() {
        return studentService.startListOfStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.registerNewStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent (@PathVariable Long id) {
        studentService.deleteNewStudent(id);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Long id,@RequestParam(required = false) String name,@RequestParam(required = false) String email) {
        studentService.updateStudent(id,name,email);
    }
}
