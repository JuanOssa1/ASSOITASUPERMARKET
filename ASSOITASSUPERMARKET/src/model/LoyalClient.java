package model;

import java.util.Calendar;

public class LoyalClient extends Client{
	private int points;
	private double discountPercent;
	private Calendar dueCard;
	private LoyalClient left; 
	private LoyalClient right;
	public LoyalClient(String id, String name, String age, String string, int points, double discountPercent,
			Calendar dueCard) {
		super(id, name, age, string);
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
	public Calendar getDueCard() {
		return dueCard;
	}
	public void setDueCard(Calendar dueCard) {
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
	
}
