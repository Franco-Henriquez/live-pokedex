package com.example.livepokedex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.livepokedex.services.SearchHistoryService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/history")

public class SearchHistoryController {
	//DELETE
	 @Autowired
	 private SearchHistoryService searchHistoryServ;
	 
	@DeleteMapping("/delete")
	public String deleteOneBook(@RequestParam("history_id") long id, Model viewModel, HttpSession session) {
//		Long instructorId = (Long) session.getAttribute("instructorId");
//		BookModel foundBook = bookServ.getOneBook(id);
		searchHistoryServ.deleteOneSearchEntry(id);
//		courseServ.removeOneCourse(id);
		Integer loadId = 1; 
		return "redirect:/dashboard";
	}
}
