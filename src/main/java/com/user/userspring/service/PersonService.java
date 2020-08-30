package com.user.userspring.service;

import com.user.userspring.Person;
import com.user.userspring.Role;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PersonService {
    void addPerson(Person person);

    Person updatePerson(Person person);

    List<Person> listPersons();

    Person getPersonById(long id);

    Person findByGivenName(String name);

    Person findByEmail(String email);

    void removePerson(long id) throws Exception;
    List<Role> getRoles();
}
