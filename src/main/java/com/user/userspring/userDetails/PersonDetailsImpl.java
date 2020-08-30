package com.user.userspring.userDetails;

import com.user.userspring.Person;
import com.user.userspring.service.PersonService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PersonDetailsImpl implements UserDetailsService {

    private PersonService personService;

    public PersonDetailsImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Person person = personService.findByEmail(email);
         if(person==null){
             throw new UsernameNotFoundException("Invalid username or password");
         }
        return  person;
    }
}
