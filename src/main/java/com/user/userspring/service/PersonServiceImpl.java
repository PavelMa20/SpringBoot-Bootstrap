package com.user.userspring.service;

import com.user.userspring.Person;
import com.user.userspring.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Transactional
    @Override
    public void addPerson(Person user) {
        user.setId(0L);
        personDao.save(user);

    }

    @Transactional
    @Override
    public Person updatePerson(Person user) {

        Optional<Person> user1 = personDao.findById(user.getId());

        if (user1.isPresent()) {
            Person upUser = user1.get();
            upUser.setGivenName(user.getGivenName());
            upUser.setSurName(user.getSurName());
            upUser.setAge(user.getAge());
            upUser.setPassword(user.getPassword());

            upUser = personDao.save(upUser);

            return upUser;
        } else
            return null;
    }

    @Transactional
    @Override
    public List<Person> listPersons() {
        List<Person> list = personDao.findAll();
        return list;
    }

    @Transactional
    @Override
    public Person getPersonById(long id) {
        Optional<Person> user = personDao.findById(id);
        return user.orElse(null);
    }

    @Transactional
    @Override
    public Person findByGivenName(String givenName) {
        return personDao.findByGivenName(givenName);
    }

    @Transactional
    @Override
    public void removePerson(long id) throws Exception {
        Optional<Person> user = personDao.findById(id);

        if (user.isPresent()) {
            personDao.deleteById(id);
        } else {
            throw new Exception("No employee record exist for given id");
        }
    }
}

