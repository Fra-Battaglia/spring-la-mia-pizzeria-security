package org.java.pizzeria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.java.pizzeria.repo.PizzaRepo;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.pojo.Pizza;

@Service
public class PizzaService {
	@Autowired
	private PizzaRepo pizzaRepo;
	
	public List<Pizza> findAll() {
		
		return pizzaRepo.findAll();
	}
	public Pizza save(Pizza pizza) {
		
		return pizzaRepo.save(pizza);
	}
	public Optional<Pizza> findById(int id) {
		
		return pizzaRepo.findById(id);
	}
	public void deletePizza(Pizza pizza) {
		
		pizzaRepo.delete(pizza);
	}
}
