package com.example.libraryHibernate.controllers;

import com.example.libraryHibernate.models.Person;

import com.example.libraryHibernate.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final PeopleService peopleService;

    public SignupController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model) {
        //model.addAttribute("user", new UserDto());
        model.addAttribute("user", new Person());
        return "signup/index";
    }

    @PostMapping
    // TODO - add validation, extend the page (confirm pass, errors, etc), add general error box for existing users
    public String registerUserAccount(@ModelAttribute("user")@Valid Person person,BindingResult result) {

        if (result.hasErrors()) {
            return "signup/index";
        }
        Optional<Person> person1 = this.peopleService.findByFullName(person.getFullName());

        if(person1.isPresent()){
            // Replace this with a message in the UI
            throw new UsernameNotFoundException("User not found");
        }

        Person p = new Person(person.getFullName(), person.getYearOfBirth(), person.getPassword());
        this.peopleService.save(p);

        // Redirect to a proper page
        return "auth/login";
    }
}
