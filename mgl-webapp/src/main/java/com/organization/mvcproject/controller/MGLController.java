package com.organization.mvcproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.organization.mvcproject.model.Game;
import com.organization.mvcproject.model.Review;
import com.organization.mvcproject.service.GameService;



@Controller
public class MGLController {

	@Autowired
	private GameService javaGameService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}
	
	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public ModelAndView review() {
	/**
	 * 
	 * update games.jsp as well. 
	 * SEE:  https://www.baeldung.com/spring-circular-view-path-error
	 */
		return new ModelAndView("createReview", "command", new Review());
	}

	@RequestMapping(value = "/addReview", method = RequestMethod.POST)
	public ModelAndView addReview(Review review, ModelMap model) {
		if(review.getAuthor().equals("")) {
			review.setAuthor("anonymous");
		}

		return new ModelAndView("reviewResult", "submittedReview", review);
	}

	
	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public ModelAndView game() {
		/**
		 * 
		 * update games.jsp as well. 
		 * SEE:  https://www.baeldung.com/spring-circular-view-path-error
		 */
		return new ModelAndView("gamesPage", "command", new Game());
	}

	/**
	 * TODO 2.0 (Separation of concerns) consider moving all controller endpoints that return a ResponseEntity into a @RestController.
	 */
	
	//TODO 1.0 RequestMapping URL should follow RESTful.
	@RequestMapping(value = "/game/get", method = RequestMethod.GET)
	public ResponseEntity<List<Game>> fetchAllGames() {
		return new ResponseEntity<List<Game>>(javaGameService.retrieveAllGames(), HttpStatus.OK);
	}

	//TODO 1.0 RequestMapping URL should follow RESTful convention
	@RequestMapping(value = "/game/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createGame(@RequestBody Game game) {
		javaGameService.saveGame(game);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}