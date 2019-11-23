package model;

public class CashRegister {
	private CommercialInvoice invoiceRoot;
	private SuperMarketApp superMarket;
	public CashRegister() {
		
	}
	public CommercialInvoice getInvoiceRoot() {
		return invoiceRoot;
	}
	public void setInvoiceRoot(CommercialInvoice invoiceRoot) {
		this.invoiceRoot = invoiceRoot;
	}
	public SuperMarketApp getSuperMarket() {
		return superMarket;
	}
	public void setSuperMarket(SuperMarketApp superMarket) {
		this.superMarket = superMarket;
	}
	
}
