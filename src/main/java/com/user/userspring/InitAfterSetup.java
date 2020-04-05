package com.user.userspring;

import com.user.userspring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.Collections;



@Component
@Scope
public class InitAfterSetup {

    @Autowired
    private PersonService personService;

    public InitAfterSetup() {

    }

    public void init() {
        Person pavel = personService.findByGivenName("pavel");
        if (pavel == null) {
            Person person = new Person("pavel", "herson", "123", 97, Collections.singleton(new Role("ADMIN")));
            personService.addPerson(person);
        }
    }
}
