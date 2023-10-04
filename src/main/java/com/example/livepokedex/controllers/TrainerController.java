package com.example.livepokedex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.livepokedex.models.Card;
import com.example.livepokedex.models.Trainer;
import com.example.livepokedex.models.TrainerLogin;
import com.example.livepokedex.services.TrainerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TrainerController {
    //------------AUTOWIRES--------------//
	@Autowired
	private TrainerService trainerServ;
	
	//----------------------------------//
    //---------------CRUD---------------//
	//----------------------------------//
	
	//--------------CREATE--------------//
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
	
	//--------------READ--------------//
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
	
	// DASHBOARD
	@GetMapping("/dashboard")
	public String landingPage(HttpSession session, Model viewModel) {
	   	if (session.getAttribute("trainerId") == null) {
	   		return "redirect:/";
	   	}
	   	Long trainerId = (Long) session.getAttribute("trainerId");
	   	System.out.println("Trainer "+ trainerId);
		Trainer thisTrainer = trainerServ.getOneTrainer(trainerId);
	   	System.out.println("thisTrainer Object"+ thisTrainer);
		List<Card> thisTrainerCardList = thisTrainer.getCardList();

	   	viewModel.addAttribute("loggedTrainer", thisTrainer);
		viewModel.addAttribute("thisTrainersCards", thisTrainerCardList);
	   	return "dashboard.jsp";
	}
}
