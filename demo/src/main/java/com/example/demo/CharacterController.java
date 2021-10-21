package com.example.demo;

//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.xml.stream.events.Characters;
import java.util.List;

@Controller
public class CharacterController {


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
    public String characterList(Model model) {

        RestTemplate restTemplate = new RestTemplate();
        List<Character> characters = restTemplate.getForObject("http://localhost:8080/characters", List.class);
        model.addAttribute("characters", characters);

        return "personList";
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }


    @GetMapping("/addPerson")
    public String showAddPersonPage(Model model) {

        CharacterForm characterForm = new CharacterForm();
        model.addAttribute("characterForm", characterForm);
//
        return "addPerson";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public String savePerson(Model model,
                             @ModelAttribute("characterForm") CharacterForm characterForm) {

        String name = characterForm.getName();
        int life = characterForm.getLife();
        int id = characterForm.getId();
        RestTemplate restTemplate = new RestTemplate();
        List<Character> characters = restTemplate.getForObject("http://localhost:8080/characters", List.class);

        Character newCharacter = new Character(name, life, id);
        restTemplate.postForObject("http://localhost:8080/characters", newCharacter, Character.class);

        return "redirect:/personList";

    }

    @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.GET)
    public String update(Model model, @PathVariable int id) {

        Character[] characters = new RestTemplate().getForObject("http://localhost:8080/characters", Character[].class);


        for (Character character : characters) {
            if (character.getId() == id) {
                CharacterForm characterForm = new CharacterForm(character.getName(), character.getLife(), character.getId());
                model.addAttribute("characterForm", characterForm);
                return "update";
            }

        }
        return "personList";
    }

    @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.POST)
    public String saveTheUpdate(Model model, @PathVariable int id, @ModelAttribute("characterForm") CharacterForm characterForm) {

        String name = characterForm.getName();
        int life = characterForm.getLife();

        Character[] characters = new RestTemplate().getForObject("http://localhost:8080/characters", Character[].class);
        for (Character character : characters) {
            if (character != null && id == character.getId()) {
                character.setName(name);
                character.setLife(life);

                new RestTemplate().put("http://localhost:8080/character/" + id, character, Character.class);
            }
        }
        return "redirect:/personList";

    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        new RestTemplate().delete("http://localhost:8080/character/" + id);
        return "redirect:/personList";
    }
}




