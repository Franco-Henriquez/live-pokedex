package com.example.livepokedex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livepokedex.models.SearchHistory;
import com.example.livepokedex.repositories.SearchHistoryRepository;

@Service
public class SearchHistoryService {
    //------------AUTOWIRES--------------//
	@Autowired
	private SearchHistoryRepository historyRepo;
	
	//----------------------------------//
    //---------------CRUD---------------//
	//----------------------------------//
	
	//--------------CREATE--------------//
	public SearchHistory addSearchHistory(SearchHistory newSearch) {
		return historyRepo.save(newSearch);
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
    
	//--------------------------------------//
    //---------------END CRUD---------------//
	//--------------------------------------//

}
