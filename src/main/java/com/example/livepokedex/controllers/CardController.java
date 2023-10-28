package com.example.livepokedex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.livepokedex.models.Card;
import com.example.livepokedex.models.ImageRecognition;
import com.example.livepokedex.models.OcrClass;
import com.example.livepokedex.models.Trainer;
import com.example.livepokedex.services.TrainerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/card")
public class CardController {
	// ------------AUTOWIRES--------------//
	@Autowired
	private TrainerService trainerServ;
	
	//CREATE
    @PostMapping("/new")
    public ResponseEntity<String> handleFormData(@RequestBody Card newPokemonCard, BindingResult result, HttpSession session) {
        // Handle the data received from the front end via AJAX
        // YourDtoClass should be a Java class representing the structure of the data
        // You can perform business logic, save data to the database, etc.
    	System.out.println("This is the data reveived from Ajax: "+newPokemonCard.getCardDeckId());
//		Long thisTrainerId = (Long) session.getAttribute("trainerId");
//		pokemonServ.addPokemon("DEX",newPokemon, result, thisTrainerId);

        // Return a response if necessary
        return ResponseEntity.ok("Data received successfully");
    }
    
    @PostMapping("/recognize-text")
    public ResponseEntity<ImageRecognition> recognizeText(@RequestBody ImageRecognition imageData) {
        // Perform OCR (text recognition) on the imageData using Tesseract or any OCR library
        String base64ImageData = imageData.getImageData();
        String otherField = imageData.getOtherField();
        
        String recognizedText = OcrClass.performOCR(base64ImageData);
        System.out.println("FROM CONTROLLER - RECOGNIZED TEXT: "+ recognizedText);
        // Create a response object with recognized text
        //ImageRecognition response = new ImageRecognition(recognizedText);
        imageData.setResponse(recognizedText);
        //ImageRecognition response = new ImageRecognition(recognizedText);
        ImageRecognition response = imageData;
        System.out.println("FROM CONTROLLER - response: "+ response.getResponse());




        
        // Return the response object and an appropriate HTTP status code
        return ResponseEntity.ok(response);
    }
    
    
    //READ
	@GetMapping("/")
	public String updateProfile(Model viewModel, HttpSession session) {
		
		  if (session.getAttribute("trainerId") == null) 
		  { 
			  return "redirect:/"; 
		  } else {
			Long trainerId = (Long) session.getAttribute("trainerId");
			Trainer thisTrainer = trainerServ.getOneTrainer(trainerId);
			viewModel.addAttribute("trainerInfo", thisTrainer);
			return "card_search.jsp";
		  }

	}
}
