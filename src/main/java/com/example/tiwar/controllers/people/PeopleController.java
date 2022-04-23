package com.example.tiwar.controllers.people;

import com.example.tiwar.models.person.Person;
import com.example.tiwar.services.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String person(Model model) {

        List<Person> allPeople = personService.findAllPeople();

        model.addAttribute("people", allPeople);
        return "/people/people";
    }

    @GetMapping("/new")
    public String addPerson() {
        return "people/new";
    }

    @GetMapping("/{person}")
    public String infoPerson(@PathVariable Person person, Model model) {
        model.addAttribute("name", person.getFirstname());
        model.addAttribute("id", person.getId());
        model.addAttribute("lastname", person.getLastname());
        model.addAttribute("path", person.getPath());
        return "/people/person";
    }

    @GetMapping("/{person}/edit")
    public String edit(@PathVariable Person person, Model model
    ) {
        model.addAttribute("name", person.getFirstname());
        model.addAttribute("age", person.getAge());
        model.addAttribute("lastname", person.getLastname());
        model.addAttribute("id", person.getId());

        return "/people/personEdit";
    }


    @PostMapping("/{person}/edit")
    public String edit(@PathVariable Person person,
                       @ModelAttribute Person per) {

//        personService.savePerson(per);
        personService.updatePerson(person, per);


        return "redirect:/people";
    }

    @PostMapping("/{person}/upload")
    public String uploadFile(@PathVariable Person person,
                             @RequestParam("file") MultipartFile multipartFile) {

        if (!multipartFile.isEmpty()) {
            personService.fileUpload(multipartFile, person);
        }
        return "redirect:/people";
    }

    @PostMapping
    public String addPerson(
           @ModelAttribute Person person
    ) {
        personService.savePerson(person);
        return "redirect:/people";
    }

    @PostMapping("/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }

}
