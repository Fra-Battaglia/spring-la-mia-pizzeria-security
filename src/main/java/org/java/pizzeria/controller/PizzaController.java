package org.java.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.java.pizzeria.service.IngredientService;
import org.java.pizzeria.service.OfferService;
//import org.java.pizzeria.service.OfferService;
import org.java.pizzeria.service.PizzaService;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.pojo.Ingredient;
import org.java.pizzeria.pojo.Offer;
import org.java.pizzeria.pojo.Pizza;
//import org.java.pizzeria.repo.PizzaRepo;

@Controller
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired 
	private IngredientService ingredientService;
	
	@GetMapping("/") 
		public String getPizzaIndex(Model model) {
			
			List<Pizza> pizze = pizzaService.findAll();
			model.addAttribute("pizze", pizze);
			
			List<Offer> offers = offerService.findAll();
			model.addAttribute("offers", offers);
			
			return "index";
		}
	
	// Create
	@GetMapping("pizza/new")
		public String createNewPizza(Model model) {
			List<Ingredient> ingredients = ingredientService.findAll();
			
			model.addAttribute("pizza", new Pizza());
			model.addAttribute("ingredients", ingredients);
			
			return "new-pizza";
		}
	
	@PostMapping("pizza/new")
		public String storeNewPizza(@ModelAttribute Pizza pizza) {
			pizzaService.save(pizza);
			
			return "redirect:/";
		}
	
	
	// Show
	@GetMapping("/pizza/{id}")
		public String getPizza(
			Model model,
			@PathVariable("id") int id
		) {
			Optional<Pizza> optPizza = pizzaService.findById(id);
			Pizza pizza = optPizza.get();
			
			Optional<Offer> optOffer = offerService.findById(id);
			Offer offer = optOffer.get();
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("offer", offer);
		
			return "pizza";
		}
	
	// Update
	@GetMapping("pizza/edit/{id}")
	public String editPizza(
				Model model,
				@PathVariable("id") int id
			) {
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		List<Ingredient> ingredients = ingredientService.findAll();
		
		Pizza pizza = pizzaOpt.get();
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		
		return "edit-pizza";
	}
	
	@PostMapping("pizza/edit/{id}")
	public String updatePizza(
			@PathVariable("id") int id,
			@ModelAttribute Pizza pizza
		) {
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	// Delete
	
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(
			@PathVariable int id
		) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		pizzaService.deletePizza(pizza);
		
		return "redirect:/";
	}
	@GetMapping("/pizza/soft-delete/{id}")
	public String softDeleteBook(
			@PathVariable int id
		) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		
		pizza.setDeleted(true);
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
//	@PostMapping("/pizza/delete/{id}")
//	public String delete(@PathVariable("id") Integer id) {
//		
//		repository.deleteById(id);
//		
//		return "redirect:/";
//	}
}
