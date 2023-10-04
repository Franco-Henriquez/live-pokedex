package com.example.livepokedex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.livepokedex.models.Card;
import com.example.livepokedex.models.Trainer;
import com.example.livepokedex.repositories.CardRepository;

@Service
public class CardService {
    //------------AUTOWIRES--------------//
	@Autowired
	private CardRepository cardRepo;
	
	//----------------------------------//
    //---------------CRUD---------------//
	//----------------------------------//
	//--------------CREATE--------------//
	//add card to list
	public Card addCard(Card newCard) {
		return cardRepo.save(newCard);
	}
	
	//add to Trainer to trainerList of card
	public void addTrainerToCard(Long cardId, Trainer thisTrainer) {
    	Optional<Card> possibleCard = cardRepo.findById(cardId);
    	System.out.println("addTrainerToCard triggered");
    	if (possibleCard.isEmpty()) {
        	System.out.println("CardId not found: "+ cardId);
    	}
    	Card thisCard = possibleCard.get();
    	
    	thisCard.getTrainerList().add(thisTrainer);
	}
	

	
	//--------------READ--------------//
	// One Card
	public Card getOneCard(Long id) {
		Optional<Card> cardOrEmpty = cardRepo.findById(id);
		return cardOrEmpty.isPresent() ? cardOrEmpty.get() : null;
	}
	// All Cards
	public List<Card> getAllCards(){
		return cardRepo.findAll();
	}
	
	
	//--------------UPDATE--------------//
	
	//--------------DELETE--------------//
    
	//--------------------------------------//
    //---------------END CRUD---------------//
	//--------------------------------------//
}
