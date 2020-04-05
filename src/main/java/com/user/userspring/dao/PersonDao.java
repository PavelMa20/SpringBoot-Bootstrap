package com.user.userspring.dao;

import com.user.userspring.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person,Long> {
    Person findByGivenName(String givenName);
}
