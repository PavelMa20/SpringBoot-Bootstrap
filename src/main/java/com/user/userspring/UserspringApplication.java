package com.user.userspring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


/*
spring.jpa.hibernate.ddl-auto = update
spring.datasource.url=jdbc:mysql://localhost:3306/users
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.jpa.show-sql = true
spring.jpa.properties.hibernate.jdbc.time_zone = UTC
 */
@SpringBootApplication
public class UserspringApplication {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(UserspringApplication.class, args);
    }

   @Bean(initMethod="init")
    public InitAfterSetup initPerson(){
        return new InitAfterSetup();
    }

}
