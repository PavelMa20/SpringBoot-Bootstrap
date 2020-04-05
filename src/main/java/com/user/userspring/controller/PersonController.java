package com.user.userspring.controller;


import com.user.userspring.Role;
import com.user.userspring.Person;
import com.user.userspring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
public class PersonController {


    @Autowired
    private PersonService personService;


    @RequestMapping("/admin/list")
    public String viewHomePage(Model model) {
        List<Person> listPersons = personService.listPersons();
        model.addAttribute("listPersons", listPersons);

        return "list";
    }

    @RequestMapping("/admin/new")
    public String showNewPersonPage(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);

        return "new_person";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person person) {
        person.setRoles(Collections.singleton(new Role("USER")));
        personService.addPerson(person);

        return "redirect:/admin/list";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String updatePerson(@ModelAttribute("person") Person person) {
        personService.updatePerson(person);

        return "redirect:/admin/list";
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
        return "redirect:/admin/list";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView helloPerson(@RequestParam(name = "id") long id) {
        ModelAndView mav = new ModelAndView("hello");
        Person person = personService.getPersonById(id);
        mav.addObject("person", person);
        return mav;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /*  @PostConstruct
    private void init() {
        User pavel = userService.findByUserName("pavel");
        if (pavel == null) {
            User user = new User("pavel", "herson", "123", 97, Collections.singleton(new Role("ADMIN")));
            userService.addUser(user);


        }
    }*/

}
