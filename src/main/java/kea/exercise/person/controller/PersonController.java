package kea.exercise.person.controller;

import kea.exercise.person.model.Person;
import kea.exercise.person.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
//
//        var person1 = new Person();
//        person1.setFirstName("TEST");
//        person1.setLastName("Spring");
//        person1.setDateOfBirth(LocalDate.parse("1992-01-01"));
//
//        var person2 = new Person();
//        person2.setFirstName("TEST2");
//        person2.setLastName("Spring2");
//        person1.setDateOfBirth(LocalDate.parse("1990-01-01"));
//
//        List<Person> persons = new ArrayList<>();
//        persons.add(person1);
//        persons.add(person2);

        return personRepository.findAll();
    }
}
