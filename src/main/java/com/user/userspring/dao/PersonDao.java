package com.user.userspring.dao;

import com.user.userspring.Person;
import com.user.userspring.Role;

import java.util.List;


public interface PersonDao  {

    void addPerson(Person person);

    Person updatePerson(Person person);

    List<Person> listPersons();

    Person getPersonById(long id);

    Person findByGivenName(String givenName);
    Person findByEmail(String email);

    void removePerson(long id);

    List<Role> getRoles();
}
