package com.example.livepokedex.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.livepokedex.models.Card;
import com.example.livepokedex.models.Trainer;
import com.example.livepokedex.models.TrainerLogin;
import com.example.livepokedex.repositories.TrainerRepository;

@Service
public class TrainerService {
    //------------AUTOWIRES--------------//
	@Autowired
	private TrainerRepository trainerRepo;

	//----------------------------------//
    //---------------CRUD---------------//
	//----------------------------------//
	//--------------CREATE--------------//
    //----------REGISTER METHOD---------//
    public Trainer register(Trainer newTrainer, BindingResult result) {
    	if(!newTrainer.getPassword().equals(newTrainer.getConfirmPassword())) {
    	    result.rejectValue("password", "passwordsDisagree.registeredTrainer.password", "The password must agree!");
    	}
    	Optional<Trainer> foundTrainer = trainerRepo.findByEmail(newTrainer.getEmail());
    	if (foundTrainer.isPresent()) {
    		result.rejectValue("email", "email.Taken.registerTrainer.email", "Email unavailable");
    	} 	
    	if (result.hasErrors()) {
    		return null;
    	} else {
    		//Password hash
    		String hashedPassword = BCrypt.hashpw(newTrainer.getPassword(), BCrypt.gensalt());
    		newTrainer.setPassword(hashedPassword);
    		trainerRepo.save(newTrainer);
    		foundTrainer = trainerRepo.findByEmail(newTrainer.getEmail());
    		return foundTrainer.get();
    	}
    	
    }
	
	//--------------READ--------------//
    //----------LOGIN METHOD----------//
    public Trainer login(TrainerLogin newLoginObject, BindingResult result) {
    	System.out.println(newLoginObject.getEmail());
        Optional<Trainer> foundTrainer = trainerRepo.findByEmail(newLoginObject.getEmail());
        if (foundTrainer.isEmpty()) {
        	result.rejectValue("email","invalidCredentials.loginTrainer.email","Invalid login credentials!");
        	return null;
        } else {
        	Trainer trainerFromDB = foundTrainer.get();
        	if(!BCrypt.checkpw(newLoginObject.getPassword(), trainerFromDB.getPassword())) {
            	result.rejectValue("email","invalidCredentials.loginTrainer.email","Invalid login credentials!");
            	return null;
        	} else {
        		return trainerFromDB;
        	}
        }
        
    }
    // Get One Trainer
    public Trainer getOneTrainer(Long trainerId) {
    	Optional<Trainer> possibleTrainer = trainerRepo.findById(trainerId);
    	if (possibleTrainer.isEmpty()) {
        	System.out.println("Trainer ID not found");
    	}
    	Trainer thisTrainer = possibleTrainer.get();
    	return thisTrainer;
    }
    
    // All cards by Trainer ID
	public List<Card> getAllCardsByTrainer(Long trainerId){
    	Optional<Trainer> possibleTrainer = trainerRepo.findById(trainerId);
    	if (possibleTrainer.isEmpty()) {
        	System.out.println("Trainer ID not found");
    	}
    	Trainer thisTrainer = possibleTrainer.get();
    	return thisTrainer.getCardList();
	}
    
    
	//--------------UPDATE--------------//
	
	//--------------DELETE--------------//
    
	//--------------------------------------//
    //---------------END CRUD---------------//
	//--------------------------------------//
}
