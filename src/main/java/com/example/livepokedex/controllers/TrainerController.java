package com.example.livepokedex.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.livepokedex.models.Card;
import com.example.livepokedex.models.Pokemon;
import com.example.livepokedex.models.SearchHistory;
import com.example.livepokedex.models.Trainer;
import com.example.livepokedex.models.TrainerLogin;
import com.example.livepokedex.repositories.SearchHistoryRepository;
import com.example.livepokedex.services.SearchHistoryService;
import com.example.livepokedex.services.TrainerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TrainerController {
	// ------------AUTOWIRES--------------//
	@Autowired
	private TrainerService trainerServ;

	@Autowired
	private SearchHistoryService searchHistoryServ;

	@Autowired
	private SearchHistoryRepository searchHistoryRepo;

	// ----------------------------------//
	// ---------------CRUD---------------//
	// ----------------------------------//

	// --------------CREATE--------------//
	// REGISTER / LOGIN
	@GetMapping("/")
	public String index(Model viewModel) {

		// Bind empty Trainer and LoginTrainer objects to the JSP
		// to capture the form input
		viewModel.addAttribute("registerTrainer", new Trainer()); // this or the one within
		viewModel.addAttribute("newLogin", new TrainerLogin());
		return "login_registration.jsp";
	}

	// REGISTER
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("registerTrainer") Trainer registerTrainer, BindingResult result,
			@ModelAttribute("newLogin") TrainerLogin newLogin,
			HttpSession session) { /* , HttpSession session, Model model */
		Trainer theNewTrainer = trainerServ.register(registerTrainer, result);
		if (result.hasErrors()) {

			return "login_registration.jsp";
		} else {
			session.setAttribute("trainerId", theNewTrainer.getId());
			return "redirect:/dashboard";
		}

	}

	// --------------READ--------------//
	// LOGIN
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") TrainerLogin newLogin, BindingResult result, Model model,
			HttpSession session) {

		Trainer foundTrainer = trainerServ.login(newLogin, result);
		if (result.hasErrors()) {
			model.addAttribute("registerTrainer", new Trainer());
			return "login_registration.jsp";
		} else {
			session.setAttribute("trainerId", foundTrainer.getId());
			return "redirect:/dashboard";
		}

	}

	// DASHBOARD // shows history of pokemon and also adds pokemon through form
	
	  @GetMapping("/dashboard") 
	  public String landingPage(@Valid @ModelAttribute("newPokemon") Pokemon newPokemon, BindingResult result, Model viewModel, HttpSession session) { 
	  if (session.getAttribute("trainerId") == null) 
	  { 
		  return "redirect:/"; 
	  }
		/*
		 * if (result.hasErrors()) { List<SearchHistory> allHistory =
		 * searchHistoryRepo.findAll();
		 * System.out.println("History List: "+Arrays.toString(allHistory.toArray()));
		 * return "dashboard.jsp"; } else {
		 */
	  Long trainerId = (Long) session.getAttribute("trainerId");
	  System.out.println("Trainer "+ trainerId); Trainer thisTrainer =
	  trainerServ.getOneTrainer(trainerId);
	  System.out.println("thisTrainer Object"+ thisTrainer); List<Card>
	  thisTrainerCardList = thisTrainer.getCardList(); 
	  //List<SearchHistory> allHistory = searchHistoryServ.getLastFour(); 
	  List<SearchHistory> allHistory = searchHistoryRepo.findAll();
	  
	  System.out.println("History List: "+Arrays.toString(allHistory.toArray()));
	  
	  viewModel.addAttribute("loadId", 1); 
	  viewModel.addAttribute("allHistory", allHistory); 
	  viewModel.addAttribute("loggedTrainer", thisTrainer);
	  viewModel.addAttribute("thisTrainersCards", thisTrainerCardList); 
	  return "dashboard.jsp";
		/* } */ 
	 }
	  
	 
	@GetMapping("/dashboard/{loadId}")
	public String landingPageWithParams(@Valid @ModelAttribute("newPokemon") Pokemon newPokemon, BindingResult result,
			@PathVariable("loadId") long loadId, Model viewModel, HttpSession session) {
		if (session.getAttribute("trainerId") == null) {
			return "redirect:/";
		}
		Long trainerId = (Long) session.getAttribute("trainerId");
		System.out.println("Trainer " + trainerId);
		Trainer thisTrainer = trainerServ.getOneTrainer(trainerId);
		System.out.println("thisTrainer Object" + thisTrainer);
		List<Card> thisTrainerCardList = thisTrainer.getCardList();
		// List<SearchHistory> allHistory = searchHistoryServ.getLastFour();
		List<SearchHistory> allHistory = searchHistoryRepo.findAll();

		System.out.println("History List: " + Arrays.toString(allHistory.toArray()));

		viewModel.addAttribute("loadId", loadId);

		viewModel.addAttribute("allHistory", allHistory);
		viewModel.addAttribute("loggedTrainer", thisTrainer);
		viewModel.addAttribute("thisTrainersCards", thisTrainerCardList);

		return "dashboard.jsp";

	}
	
	// --------------UPDATE--------------//
	// TRAINER PROFILE
	@GetMapping("/trainer")
	public String updateProfile(Model viewModel, HttpSession session) {
		
		  if (session.getAttribute("trainerId") == null) 
		  { 
			  return "redirect:/"; 
		  } else {
			Long trainerId = (Long) session.getAttribute("trainerId");
			Trainer thisTrainer = trainerServ.getOneTrainer(trainerId);
			viewModel.addAttribute("trainerInfo", thisTrainer);
			return "edit_trainer_profile.jsp";
		  }
		  
		  

	}
	
	//UPDATE - FORM SUBMIT CHANGES
	@PutMapping("/trainer/update")
	public String updateTrainer(@Valid @ModelAttribute("trainerInfo") Trainer thisTrainer, BindingResult result, Model viewModel, HttpSession session ) {
		//dumb issue, BindingResult needs to always be closest to its ModelAttribute?! Binding Result is tied to the form and will change on edit, must store information via viewModel again
		if (session.getAttribute("trainerId") == null) 
		{
			return "redirect:/";
	   	}
	   	if (result.hasErrors()) {
			return "edit_trainer_profile.jsp";
		} else {
		//Long trainerId = (Long) session.getAttribute("trainerId");
		viewModel.addAttribute("trainerInfo", thisTrainer);
		
		
		trainerServ.updateTrainer(thisTrainer, result);
		return "redirect:/trainer";
		}
	}

	

	// --------------DELETE--------------//
	// LOG OUT
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";

	}

}
