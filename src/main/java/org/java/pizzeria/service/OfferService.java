package org.java.pizzeria.service;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.pojo.Offer;
import org.java.pizzeria.pojo.Pizza;
import org.java.pizzeria.repo.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
	
	@Autowired
	private OfferRepo offerRepo;
	
	public List<Offer> findAll() {
		return offerRepo.findAll();
	}
	
	public Optional<Offer> findById(int id) {
		
		return offerRepo.findById(id);
	}
	
	public Offer save(Offer offer) {
		return offerRepo.save(offer);
	}
}
