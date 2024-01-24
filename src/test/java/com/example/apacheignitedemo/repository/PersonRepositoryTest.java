package com.example.apacheignitedemo.repository;

import com.example.apacheignitedemo.model.PersonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void findByName() {
        personRepository.deleteAll();
        var personEntity = new PersonEntity(1L, "name");
        personRepository.save(1L, personEntity);

        List<PersonEntity> personEntities = personRepository.findByName("name");
        for (PersonEntity entity : personEntities) {
            assert entity.getName().equals("name");
        }

        personRepository.deleteAll();
    }

    @Test
    void selectPersonsWithIdGreaterThan() {
    }
}