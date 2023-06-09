package org.java.pizzeria.controller;

import java.util.Iterator;
import java.util.List;

import org.java.pizzeria.pojo.Ingredient;
import org.java.pizzeria.pojo.Pizza;
import org.java.pizzeria.service.IngredientService;
import org.java.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired 
	private PizzaService pizzaService;
	
	@GetMapping("/ingredient")
		public String ingredientIndex(Model model) {
			List<Ingredient> ingredients = ingredientService.findAll();
			model.addAttribute("ingredients", ingredients);
			
		return "ingredient-index";
	}
	
	@GetMapping("/ingredient/{id}/delete")
		public String ingredientDelete(@PathVariable int id) {
			Ingredient ingredient = ingredientService.findById(id).get();
			for (Pizza p : ingredient.getPizze() ) {
				p.removeIngredient(ingredient);
				pizzaService.save(p);
			}
			
			ingredientService.delete(ingredient);
			
		return "redirect:/ingredient";
	}
	
	@GetMapping("/ingredient/new")
		public String createNewIngredient(Model model) {
			model.addAttribute("ingredient", new Ingredient());
		return "new-ingredient";
	}
	
	@PostMapping("/ingredient/new")
		public String storeNewIngredient(
				Model model, 
				@ModelAttribute Ingredient ingredient) {
			ingredientService.save(ingredient);
			
			return "redirect:/ingredient";
	}
}
