package model;

import java.util.ArrayList;

public class CommercialInvoice {
	private String date;
	private double totalPrice;
	private String paymentType;
	private String factureNumber;
	private CommercialInvoice next; 
	private CommercialInvoice previus;
	private Client client;
	private ArrayList<Product> products;
	public static final double IVA = 0.19;
	public CommercialInvoice(String date,/* double totalPrice,*/ String paymentType, String factureNumber, ArrayList<Product> products, Client client) {
		super();
		this.date = date;
		/*this.totalPrice = totalPrice;*/
		this.paymentType = paymentType;
		this.factureNumber = factureNumber;
		//products = new ArrayList<Product>();
		this.products = products;
		this.products = /*new ArrayList<Product>()*/ products;
		this.client = client;
	}
	public CommercialInvoice getPrevius() {
		return previus;
	}
	public void setPrevius(CommercialInvoice previus) {
		this.previus = previus;
	}
	public CommercialInvoice getNext() {
		return next;
	}
	public void setNext(CommercialInvoice next) {
		this.next = next;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getFactureNumber() {
		return factureNumber;
	}
	public void setFactureNumber(String factureNumber) {
		this.factureNumber = factureNumber;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	public double calculateLoyalDiscount() {
		double discount = 0;
		if(client instanceof LoyalClient) {
			discount = ((LoyalClient) client).getDiscountPercent();
		}
		return discount;
	}
	public double subTotal() {
		double totalWeight = 0;
		double totalUnity = 0;
		double total = 0;
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i) instanceof WeightProduct) {
				//Manager manager = (Manager) workers.get(i);
				WeightProduct weight = (WeightProduct) products.get(i);
				totalWeight = weight.getPrice()*weight.getWeight();
			} else {
				UnityProduct unity = (UnityProduct) products.get(i);
				totalUnity = unity.getPrice()*unity.getQuantity();
			}
		}
		total = totalUnity + totalWeight;
		return total;
	}
	public double calculateSavings() {
		return subTotal()*calculateLoyalDiscount();
	}
	public double calculateIVA() {
		double tax = subTotal()*IVA;
		return tax;
	}
	public /*double*/ void total() {
		//double total = (subTotal()-calculateSavings())+calculateIVA();
		//return total;
		totalPrice =  (subTotal()-calculateSavings())+calculateIVA();
	}
	public String printProducts() {
		String msg = "";
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i) instanceof WeightProduct) {
				//Manager manager = (Manager) workers.get(i);
				WeightProduct weight = (WeightProduct) products.get(i);
				msg = weight.toString() + "\n";
			} else {
				UnityProduct unity = (UnityProduct) products.get(i);
				msg = unity.toString() + "\n";
			}
		}
		return msg;
	}
	@Override
	public String toString() {
		return "CommercialInvoice [date=" + date + ", totalPrice=" + totalPrice + ", paymentType=" + paymentType
				+ ", factureNumber=" + factureNumber + ", client=" + client + ", products=" + products + "]";
	}
	
}
