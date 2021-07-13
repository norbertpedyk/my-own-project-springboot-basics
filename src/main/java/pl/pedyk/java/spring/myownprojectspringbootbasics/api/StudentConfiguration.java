package pl.pedyk.java.spring.myownprojectspringbootbasics.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pedyk.java.spring.myownprojectspringbootbasics.dao.StudentRepository;
import pl.pedyk.java.spring.myownprojectspringbootbasics.model.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student jan_nowak = new Student("Jan Nowak", LocalDate.of(1995, Month.SEPTEMBER, 23), "jan.nowak@gmail.com");
            Student katarzyna_bak = new Student("Katarzyna Bak", LocalDate.of(1991, Month.OCTOBER, 20), "kasia.bak@gmail.com");
            Student krzysztof_krawczyk = new Student("Krzysztof Krawczyk", LocalDate.of(1990, Month.JANUARY, 2), "krzysztof.k@gmail.com");
            Student ada_chodakowska = new Student("Ada Chodakowska", LocalDate.of(1981, Month.MAY, 9), "chodakowska@gmail.com");

            repository.saveAll(List.of(jan_nowak,katarzyna_bak,krzysztof_krawczyk,ada_chodakowska));
        };
    }
}
