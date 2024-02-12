package kea.exercise.person.controller;

import kea.exercise.person.model.Person;
import kea.exercise.person.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        Optional<Person> person = personRepository.findById(id);
        return ResponseEntity.of(person);
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        Optional<Person> original = personRepository.findById(id);
        if (original.isPresent()) {
            Person originalPerson = original.get();
            // Update person
            originalPerson.setFirstName(person.getFirstName());
            originalPerson.setLastName(person.getLastName());
            originalPerson.setDateOfBirth(person.getDateOfBirth());
            // Save to DB and return the updated person
            Person updatedPerson = personRepository.save(originalPerson);
            return ResponseEntity.ok().body(updatedPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable int id) {
        Optional<Person> person = personRepository.findById(id);
        personRepository.deleteById(id);
        return ResponseEntity.of(person);
    }
}
