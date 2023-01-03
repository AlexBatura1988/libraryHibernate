package com.example.libraryHibernate.controllers;

import com.example.libraryHibernate.models.Person;
import com.example.libraryHibernate.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people",peopleService.findAll());
        return"people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("books",peopleService.getBookByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")Person person){
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person")@Valid Person person, BindingResult result){
        if (result.hasErrors()){
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }
}
