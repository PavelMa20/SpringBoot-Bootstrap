package com.user.userspring.service;

import com.user.userspring.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    void addPerson(Person person);

    Person updatePerson(Person person);

    List<Person> listPersons();

    Person getPersonById(long id);

    Person findByGivenName(String name);

    void removePerson(long id) throws Exception;
}
