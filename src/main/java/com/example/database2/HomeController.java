package com.example.database2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    PokemonRepository pokemonRepo;
    @Autowired
    TrainerRepository trainerRepo;

    @RequestMapping("/")
    public String homePage(Model model){
        model.addAttribute("trainers", trainerRepo.findAll());
        model.addAttribute("pokemons", pokemonRepo.findAll());
        return "index";
    }
    @GetMapping("/addpokemon")
    public String addPokemon(Model model){
        model.addAttribute("trainers", trainerRepo.findAll());
        model.addAttribute("pokemon", new Pokemon());
        return "pokemonform";
    }
    @PostMapping("/processpokemon")
    public String processPokemon(Pokemon pokemon){
        pokemonRepo.save(pokemon);
        return "redirect:/";
    }
    @GetMapping("/addtrainer")
    public String addTrainer(Model model){
        model.addAttribute("trainer", new Trainer());
        return "trainerform";
    }
    @PostMapping("/processtrainer")
    public String processTrainer(Trainer trainer){
        trainerRepo.save(trainer);
        return "redirect:/";
    }
    @RequestMapping("/detailtrainer/{id}")
    public String showTrainer(@PathVariable("id") long id, Model model){
        model.addAttribute("trainer", trainerRepo.findById(id).get());
        return "trainerinfo";
    }
    @RequestMapping("/updatetrainer/{id}")
    public String updateTrainer(@PathVariable("id") long id, Model model){
        model.addAttribute("trainer", trainerRepo.findById(id).get());
        return "trainerform";
    }
    @RequestMapping("/deletetrainer/{id}")
    public String delTrainer(@PathVariable("id") long id){
        trainerRepo.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showPokemon(@PathVariable("id") long id, Model model){
        model.addAttribute("pokemon", pokemonRepo.findById(id).get());
        return "pokemoninfo";
    }
    @RequestMapping("/update/{id}")
    public String updatePokemon(@PathVariable("id") long id, Model model){
        model.addAttribute("trainers", trainerRepo.findAll());
        model.addAttribute("pokemon", pokemonRepo.findById(id).get());
        return "pokemonform";
    }
    @RequestMapping("/delete/{id}")
    public String delPokemon(@PathVariable("id") long id){
        pokemonRepo.deleteById(id);
        return "redirect:/";
    }

}
