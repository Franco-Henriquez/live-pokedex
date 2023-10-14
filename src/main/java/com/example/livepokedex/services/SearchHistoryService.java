package com.example.livepokedex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livepokedex.models.Card;
import com.example.livepokedex.models.Pokemon;
import com.example.livepokedex.models.SearchHistory;
import com.example.livepokedex.models.Trainer;
import com.example.livepokedex.repositories.SearchHistoryRepository;

@Service
public class SearchHistoryService {
    //------------AUTOWIRES--------------//
	@Autowired
	private SearchHistoryRepository historyRepo;
	@Autowired
	private TrainerService trainerServ;
	/*
	 * @Autowired private PokemonService pokemonServ;
	 */
	@Autowired
	private CardService cardServ;
	
	
	
	//----------------------------------//
    //---------------CRUD---------------//
	//----------------------------------//
	
	//--------------CREATE--------------//
	public SearchHistory addSearchHistory(SearchHistory newSearch) {
		return historyRepo.save(newSearch);
	}
	
	public SearchHistory generateSearchHistory(String searchType, Pokemon pokemon, Long TrainerId) {
		System.out.println("Starting to add pokemon history.."+searchType+ " " + pokemon.getId()+ " " +TrainerId);
		
       	Trainer foundTrainer = trainerServ.getOneTrainer(TrainerId);
		if (searchType == "DEX") {
			//mock card
			Card mockCard = cardServ.getOneCard(1L);
			
	       	//Pokemon foundPokemon = pokemonServ.getOnePokemon(searchStringId);		
			SearchHistory thisSearch = new SearchHistory(searchType, Long.toString(pokemon.getId()), null, foundTrainer, pokemon);
			System.out.println("Added pokemon history");
			return historyRepo.save(thisSearch);
		} 
		else if (searchType == "CARD")
		{
			
			//create a card server here
			   	return null;
		}
		//if statement for CARD or DEX searchType
		//find the searchString match by id  
		// find and get the trainer object
		// get the searchString object
		
		
		return null;
	}

	//--------------READ--------------//
	// Last 4 Entries
	public List<SearchHistory> getLastFour() {
		return historyRepo.findAll(); //FIX: need a way to only get the last far
		//maybe order them in ascending order and get the first 4 that show up.
		//may need to use advanced query
	}
	

	//--------------UPDATE--------------//
	
	//--------------DELETE--------------//
	public void deleteOneSearchEntry(Long searchHistoryId) {
		historyRepo.deleteById(searchHistoryId);
	}
    
	//--------------------------------------//
    //---------------END CRUD---------------//
	//--------------------------------------//

}
