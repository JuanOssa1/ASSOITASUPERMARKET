package model;

import java.io.Serializable;
import java.util.Calendar;

public class LoyalClient extends Client implements Serializable{
	private int points;
	private double discountPercent;
	private String dueCard;
	private LoyalClient left; 
	private LoyalClient right;
	public LoyalClient(String id, String name, String age, String email, int points, double discountPercent, String dueCard) {
		super(id, name, age, email);
		this.points = points;
		this.discountPercent = discountPercent;
		this.dueCard = dueCard;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public double getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}
	public String getDueCard() {
		return dueCard;
	}
	public void setDueCard(String dueCard) {
		this.dueCard = dueCard;
	}
	public LoyalClient getLeft() {
		return left;
	}
	public void setLeft(LoyalClient left) {
		this.left = left;
	}
	public LoyalClient getRight() {
		return right;
	}
	public void setRight(LoyalClient right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "LoyalClient [points=" + points + ", discountPercent=" + discountPercent + ", dueCard=" + dueCard
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getAge()=" + getAge() + ", getEmail()="
				+ getEmail() + "]";
	}
	
	
}
