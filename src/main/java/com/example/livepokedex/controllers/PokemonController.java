package com.example.livepokedex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.livepokedex.models.Pokemon;
import com.example.livepokedex.models.Trainer;
import com.example.livepokedex.services.PokemonService;
import com.example.livepokedex.services.SearchHistoryService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {
	 @Autowired
	 private PokemonService pokemonServ;
	 
	 @Autowired
	 private SearchHistoryService searchHistoryServ;
	
	//CREATE - FORM SUBMIT
	//ADD POKEMON AND SEARCH HISTORY
	@PostMapping("/new") //issue with this is that it refreshes the page
	public String addPokemonToDb(@Valid @ModelAttribute("newPokemon") Pokemon newPokemon, Model viewModel, BindingResult result, HttpSession session) {
	   	if (session.getAttribute("trainerId") == null) {
	   		return "redirect:/";
	   	}
		if (result.hasErrors()) {
			return "dashboard.jsp";
		} else {
			Long thisTrainerId = (Long) session.getAttribute("trainerId");
			pokemonServ.addPokemon("DEX",newPokemon, result, thisTrainerId);
			System.out.println("New pokemon added: "+newPokemon.getId());
			
			//searchType = Dex, searchString = pokeid, trainer id, 0) // we use 0 for poke id as we're just passing it anyways in argument pos 2
			
			//newPokemon returns null, likely because it was never added to the database because it already exists
			//to fix this, we will move the below code to pokemonServ and do a check to see if the pokemon already exists
			// and if it does, then we fetch the id and this should fix that issue
			
			
			Integer loadId = 2; 
			
			return "redirect:/dashboard/"+loadId; //"redirect:/dashboard";  no redirect otherwise we reset the apge
		}
	}
}
