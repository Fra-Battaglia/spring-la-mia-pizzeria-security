package org.java.pizzeria.pojo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	
	private LocalDate startDate;
	private LocalDate endDate;
	private String title;
	private Float discount;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;
	
	public Offer() { }
	public Offer(LocalDate startDate, LocalDate endDate, String title, Float discount, Pizza pizza) {
		setStartDate(startDate);
		setEndDate(endDate);
		setTitle(title);
		setDiscount(discount);
		setPizza(pizza);
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	public Float getFinalPrice() {
		
		return (pizza.getPrice() * (100 - getDiscount())) / 100f;
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "] " 
		+ "\nstart date: " + getStartDate() 
		+ "\nend date: " + getEndDate() 
		+ "\ntitle:" + getTitle() 
		+ "\ndiscount=" + getDiscount();
	}
}
