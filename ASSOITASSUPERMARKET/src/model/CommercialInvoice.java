package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CommercialInvoice implements Serializable{
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
	/**
	 * Description: Permite obtener la factura anterior a la cual se encuentra poscionada
	 * @return Factura anterior a la poscion actual
	 */
	public CommercialInvoice getPrevius() {
		return previus;
	}
	/**
	 * Description: Permite cambiar la factura anterior de la factura actual de la lista
	 * @param previus 
	 */
	public void setPrevius(CommercialInvoice previus) {
		this.previus = previus;
	}
	/**
	 *Description: Permite obtener la siguiente factura de la factura actual de la lista
	 * @return
	 */
	public CommercialInvoice getNext() {
		return next;
	}
	/**
	 * Description: Permite cambiar la siguiente factura de la facutra actual de la lista 
	 * @param next
	 */
	public void setNext(CommercialInvoice next) {
		this.next = next;
	}
	/**
	 * Description: Permite obtener la fecha de creacion de la factura
	 * @return fecha en string de la creacion de la factura
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Description: Permite cambiar la fecha de creacion de la factura
	 * @param date Nueva fecha que se quiere asignar
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * Description: Permite obtener el precio total de la factura
	 * @return El valor en double de la factura
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * Description: Permite cambiar el valor total de la factura 
	 * @param totalPrice nuevo precio total que se quiere asignar
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * Description: Permite obtener el tipo de pago con el que se pago la factura
	 * @return Una cadena con el tipo de pago
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * Description: Permite modificar el tipo de pago con que se pago la factura
	 * @param paymentType El nueo tipo de pago que quiere asignar a la factura;
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * Description: Permite obtener el numero de factura asignado
	 * @return Una cadena con el numero de la factura
	 */
	public String getFactureNumber() {
		return factureNumber;
	}
	/**
	 * Description: Permite cambiar el numero de la factura con la que fue creada
	 * @param factureNumber nuevo numero de factura que se quiere asignar
	 */
	public void setFactureNumber(String factureNumber) {
		this.factureNumber = factureNumber;
	}
	/**
	 * Description: Permite obtener el cliente el cual compro la factura
	 * @return Cliente duenio de la compra
	 */
	public Client getClient() {
		return client;
	}
	/**
	 * Description: Permite cambiar el cliente deunio de la factura
	 * @param client Nuevo cliente que quiero asignar
	 */
	public void setClient(Client client) {
		this.client = client;
	}
	/**
	 * Description: Permite obtener el arryalist que contiene cada uno de los productos de la facutura
	 * @return ArrayList con los productos aniadidos
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}
	/**
	 * Description: Permite cambiar el ArrayList de productos previamente aniadido a la factura
	 * @param products Nuevo ArrayList de productos
	 */
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	/**
	 * Description: Permite obtener el descuento que tiene un cliente leal, en caso de que no se un cliente leal no tendra descuento
	 * @return discount Descuento que tiene un cliente leal asignado, si no es cliente leal no tenra descuento
	 */
	public double calculateLoyalDiscount() {
		double discount = 0;
		if(client instanceof LoyalClient) {
			discount = ((LoyalClient) client).getDiscountPercent();
		}
		return discount;
	}
	/**
	 *Description: Permite calcular el valor total neto de los productos antes de asignar descuentos y adiciones
	 * @return total Valor en double del total de los productos sin descuentos y adiciones
	 */
	public double subTotal() {
		double totalWeight = 0;
		double totalUnity = 0;
		double total = 0;
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i) instanceof WeightProduct) {
				//Manager manager = (Manager) workers.get(i);
				WeightProduct weight = (WeightProduct) products.get(i);
				totalWeight += weight.getPrice()*weight.getWeight();
			} else {
				UnityProduct unity = (UnityProduct) products.get(i);
				totalUnity += unity.getPrice()*unity.getQuantity();
			}
		}
		total = totalUnity + totalWeight;
		return total;
	}
	//58100*0.3 = 17430
	//iva 11039
	/**
	 * Description: Permite dar el valor ahorrado por el cliente leal
	 * @return Valor en double de lo ahorrado por el cliente leal
	 */
	public double calculateSavings() {
		return subTotal()*calculateLoyalDiscount();
	}
	/**
	 * Description: Permite calcular el IVA general de la compra
	 * @return Valor de lo ncesario a pagar correspondiente al impuesto;
	 */
	public double calculateIVA() {
		double tax = subTotal()*IVA;
		return tax;
	}
	/**
	 * Description: Permite calcular el valor total que el usuariodebera pagar despues de adiciones y descuentos 
	 * @return totalPrice Valor total que el usuario debe pagar
	 */
	public double /*void*/ total() {
		//double total = (subTotal()-calculateSavings())+calculateIVA();
		//return total;
		return totalPrice =  (subTotal()-calculateSavings())+calculateIVA();
	}
	/**
	 * Description: Lanza un mensaje de tipo String con la iformacion de cada uno de os productos 
	 * @return Una linea con cada uno de los procutos de la factura
	 */
	public String printProducts() { 
		String msg = "";
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i) instanceof WeightProduct) {
				//Manager manager = (Manager) workers.get(i);
				WeightProduct weight = (WeightProduct) products.get(i);
				msg += weight.toString() + "\n";
			} else {
				UnityProduct unity = (UnityProduct) products.get(i);
				msg += unity.toString() + "\n";
			}
		}
		return msg;
	}
	/**
	 *Description: Convierte cada uno de los atributos de la clase CommercialInvoice a objetos de tipo String 
	 *@return Un mensaje de tipo String con cada uno de los atributos de la clase commercial Invoice
	 */
	@Override
	public String toString() {
		return "CommercialInvoice [date=" + date + ", totalPrice=" + totalPrice + ", paymentType=" + paymentType
				+ ", factureNumber=" + factureNumber + ", client=" + client + ", products=" + products + "]";
	}
	
}
