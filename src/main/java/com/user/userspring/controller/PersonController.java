package com.user.userspring.controller;

import com.user.userspring.Person;
import com.user.userspring.Role;
import com.user.userspring.service.PersonService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Collections;
import java.util.List;




@Controller
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @RequestMapping("/admin")
    public String viewHomePage(Model model) {
        Person person = getLoginPerson();
        model.addAttribute("headPerson", person);
        List<Person> listPersons = personService.listPersons();
        model.addAttribute("listPersons", listPersons);
        model.addAttribute("newPerson", new Person());
        model.addAttribute("rolesList", personService.getRoles());
        return "admin";
    }


    @RequestMapping("/admin/new")
    public String showNewPersonPage(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "new_person";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("newPerson") Person person) {
        personService.addPerson(person);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String updatePerson(@ModelAttribute("person") Person person) {
        personService.updatePerson(person);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/edit")
    public ModelAndView showEditPersonPage(@RequestParam(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_person");
        Person person = personService.getPersonById(id);
        mav.addObject("person", person);
        return mav;
    }

    @RequestMapping("/admin/delete")
    public String deletePerson(@RequestParam(name = "id") long id) throws Exception {
        personService.removePerson(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView helloPerson() {
        ModelAndView mav = new ModelAndView("user");
        Person person = getLoginPerson();
        mav.addObject("headPerson", person);
        mav.addObject("person", person);
        return mav;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


   private Person getLoginPerson() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loginName = auth.getName();
        Person loginPerson = personService.findByGivenName(loginName);
        if (loginPerson == null) {
            Person newGooglePerson = new Person();
            DefaultOidcUser googleUser = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Person checkGoogleUser = personService.findByEmail(googleUser.getEmail());
            if(!(checkGoogleUser==null)){
                return checkGoogleUser;
            }
            newGooglePerson.setGivenName(googleUser.getGivenName());
            newGooglePerson.setSurName(googleUser.getFamilyName());
            newGooglePerson.setAge(20);
            newGooglePerson.setEmail(googleUser.getEmail());
            newGooglePerson.setPassword("12345");
            newGooglePerson.setRoles(Collections.singleton(new Role("2")));
            personService.addPerson(newGooglePerson);
            return newGooglePerson;
        } else {
            return loginPerson;
        }

    }

}
