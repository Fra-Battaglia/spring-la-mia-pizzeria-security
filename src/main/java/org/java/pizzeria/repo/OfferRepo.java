package org.java.pizzeria.repo;

import org.java.pizzeria.pojo.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepo extends JpaRepository<Offer, Integer>{

}
