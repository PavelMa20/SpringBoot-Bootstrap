package com.user.userspring.service;

import com.user.userspring.Person;
import com.user.userspring.Role;
import com.user.userspring.dao.PersonDao;
import com.user.userspring.dao.RoleDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonDao personDao;
    private RoleDao roleDao;

    public PersonServiceImpl(PersonDao personDao, RoleDao roleDao) {
        this.personDao = personDao;
        this.roleDao = roleDao;
    }


    @Transactional
    @Override
    public void addPerson(Person person) {

        List<Long> rolesIds = person.getRoles().stream().map(Role::getId).collect(Collectors.toList());
        person.setRoles(roleDao.getRolesFromIds(rolesIds));
        personDao.addPerson(person);

    }

    @Transactional
    @Override
    public Person updatePerson(Person person) {
        return personDao.updatePerson(person);
    }


    @Override
    public List<Person> listPersons() {
        List<Person> list = personDao.listPersons();
        return list;
    }

    @Transactional
    @Override
    public Person getPersonById(long id) {
        return personDao.getPersonById(id);
    }

    @Transactional
    @Override
    public Person findByGivenName(String givenName) {
        return personDao.findByGivenName(givenName);
    }

    @Transactional
    @Override
    public Person findByEmail(String email) {
        return personDao.findByEmail(email);
    }

    @Transactional
    @Override
    public void removePerson(long id) throws Exception {
        personDao.removePerson(id);
    }

    @Override
    public List<Role> getRoles() {
        return personDao.getRoles();
    }
}

