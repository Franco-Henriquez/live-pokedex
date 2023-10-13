package com.example.livepokedex.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.livepokedex.models.Pokemon;
import com.example.livepokedex.models.Trainer;
import com.example.livepokedex.repositories.PokemonRepository;

@Service
public class PokemonService {
    //------------AUTOWIRES--------------//
	@Autowired
	private PokemonRepository pokemonRepo;
	
	@Autowired
	private TrainerService trainerServ;
	@Autowired
	private SearchHistoryService searchHistoryServ;
	
	/* private PokemonService self; */
	//----------------------------------//
    //---------------CRUD---------------//
	//----------------------------------//
	//--------------CREATE--------------//
	public Pokemon addPokemon(String SearchType, Pokemon newPokemon, BindingResult result, Long thisTrainer) {
    	Optional<Pokemon> foundPokemon = pokemonRepo.findByDexId(newPokemon.getDexId());
    	if (foundPokemon.isPresent()) {
    		result.rejectValue("dexId", "dexid.Taken.addPokemon.dexid", "DexId Found - Pokemon already added to the Pokemon table - unable to add");
    		//this error won't be shown, the end-user doesn't need to know this//
    		//but we are troubleshooting in terminal:
    		System.out.println("DexId Found - Pokemon already added to the Pokemon table - unable to add");
    		
    		//IF POKEMON FOUND, ADD TO HISTORY ALONG WITH TRAINER INFO
    		System.out.println("Possible Trainer :"+thisTrainer);
    		searchHistoryServ.generateSearchHistory(SearchType,foundPokemon.get(),thisTrainer);
           	Trainer foundTrainer = trainerServ.getOneTrainer(thisTrainer);
           	Pokemon thisPokemon = foundPokemon.get();
           	thisPokemon.getTrainerList().add(foundTrainer);
    		return pokemonRepo.save(thisPokemon);

    	}
    	if (result.hasErrors()) {
    		return null;
    	} else {
       	Trainer foundTrainer = trainerServ.getOneTrainer(thisTrainer);
       	//List<Trainer> listTrainer = (List<Trainer>) foundTrainer;
       	
       	//newPokemon.setTrainerList((List<Trainer>) foundTrainer); //was trying to add the trainer before saving it but didn't let me
       	pokemonRepo.save(newPokemon);
       	Optional<Pokemon> newlyAddedPokemon = pokemonRepo.findByDexId(newPokemon.getDexId()); //get from db for now
       	Pokemon thisPokemon = newlyAddedPokemon.get();
       	//thisPokemon.setTrainerList(listTrainer);
       	//thisPokemon.getTrainerList().add(foundTrainer);
		searchHistoryServ.generateSearchHistory(SearchType,newPokemon,thisTrainer);
		//addTrainerToPokemon(foundTrainer, thisPokemon);
		List<Trainer> trainerList = new ArrayList();
		trainerList.add(foundTrainer);
		thisPokemon.setTrainerList(trainerList);
       	pokemonRepo.save(thisPokemon);

       	
       	//if pokemon not found, then create it/add it
       	//Pokemon thisPokemon = foundPokemon.get();
       	//thisPokemon.getTrainerList().add(foundTrainer);
       	

    	//IF POKEMON NOT FOUND, ADD TO POKEMON TABLE AND
    	//...ADD TO HISTORY ALONG WITH TRAINER INFO
		return null;
    	}
	}
	
	public Pokemon addTrainerToPokemon(Trainer trainer, Pokemon pokemon) {
		//pokemon.getTrainerList().add(trainer);
		//pokemon.setTrainerList(List<Trainer> trainer);
		List<Trainer> trainerList = new ArrayList();
		trainerList.add(trainer);
		pokemon.setTrainerList(trainerList);
       	pokemonRepo.save(pokemon);
		
		return null;

	}
	
	
	
	//--------------READ--------------//
    public Pokemon getOnePokemon(Long pokemonId) {
    	Optional<Pokemon> possiblePokemon = pokemonRepo.findById(pokemonId);
    	if (possiblePokemon.isEmpty()) {
        	System.out.println("Pokemon ID not found");
    	}
    	Pokemon thisPokemon = possiblePokemon.get();
    	return thisPokemon;
    }	

	


}
