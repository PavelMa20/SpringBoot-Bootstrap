package com.user.userspring;

import com.user.userspring.service.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Component
public class TestDataInit {

    private PersonService personService;

    public TestDataInit(PersonService personService) {
        this.personService = personService;
    }

    @PostConstruct
    @Transactional
    public void init() {

        Person pavel = personService.findByEmail("pasha@mail.ru");
        if (pavel == null) {
            Person person = new Person("pavelAdmin", "herson","pasha@mail.ru", "123", 97);
            Role role = new Role();
            role.setRoleName("ADMIN");
            person.setRoles( Collections.singleton(role));
            personService.addPerson(person);
        }
    }
}
