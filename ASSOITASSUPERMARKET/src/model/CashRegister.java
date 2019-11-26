package model;

import java.util.ArrayList;

import exceptions.noMatchesException;
import exceptions.repeatedCustomerException;
import exceptions.unavaiableIdException;
import logicInterfaces.InvoiceUpdater;

public class CashRegister implements InvoiceUpdater{
	private CommercialInvoice firstInvoice;
	private SuperMarketApp superMarket;
	public CashRegister() {
		superMarket = new SuperMarketApp();
	}
	public CommercialInvoice getInvoiceRoot() {
		return firstInvoice;
	}
	public void setInvoiceRoot(CommercialInvoice invoiceRoot) {
		this.firstInvoice = invoiceRoot;
	}
	public SuperMarketApp getSuperMarket() {
		return superMarket;
	}
	public void setSuperMarket(SuperMarketApp superMarket) {
		this.superMarket = superMarket;
	}
	public void addInvoiceProductToTheList(String date/*, double totalPrice*/, String paymentType, String factureNumber/*, ArrayList<Product> products*/, Client client) throws unavaiableIdException {
		validateAvaiablityOfTheFactureNumber(factureNumber);
		CommercialInvoice commercialInvoice = new CommercialInvoice(date, /*totalPrice,*/paymentType, factureNumber, products, client);
		if(firstInvoice == null) {	
			firstInvoice = commercialInvoice;
		}else {
			CommercialInvoice newReference = firstInvoice;
			while(newReference.getNext()!=null) {
				newReference = newReference.getNext();
			}
			newReference.setNext(commercialInvoice);
			commercialInvoice.setPrevius(newReference);
		}
		
	}
	public String searchInvoice(String factureNumber) throws noMatchesException {
		validateExistenceOfFactureNumber(factureNumber);
		CommercialInvoice newReference = firstInvoice;
		String msg = "";
		boolean centinel = false;
		while(newReference != null && centinel == false) {
			if(newReference.getFactureNumber().equals(factureNumber)) {
				msg = newReference.toString();
				centinel = true;
			}
			newReference = newReference.getNext();
		}
		return msg;
	}
	private void validateExistenceOfFactureNumber(String factureNumber) throws noMatchesException {
		CommercialInvoice newReference = firstInvoice;
		String msg = "";
		boolean centinel = false;
		while(newReference != null && centinel == false) {
			if(newReference.getFactureNumber().equals(factureNumber)) {
				msg = newReference.toString();
				centinel = true;
			}
			newReference = newReference.getNext();
		}
		if(msg == "") {
			throw new noMatchesException("Fatal failure! Check!");
		}
	}
	public String updateInvoiceData(String date, double totalPrice, String paymentType, String factureNumber) throws noMatchesException {
		validateExistenceOfFactureNumber(factureNumber);
		CommercialInvoice commercialInvoiceToUpdate = firstInvoice;
		String msg = "";
		boolean centinel = false;
		while(commercialInvoiceToUpdate != null && centinel == false) {
			if(commercialInvoiceToUpdate.getFactureNumber().equals(factureNumber)) {
					msg ="El personaje actualizado fue:"+" "+ commercialInvoiceToUpdate.toString();
					updateInformartionInvoice(commercialInvoiceToUpdate,  date,  totalPrice,  paymentType,  factureNumber );
					centinel = true;
			}
			commercialInvoiceToUpdate = commercialInvoiceToUpdate.getNext();
		}
		return msg;
	}
	private void validateAvaiablityOfTheFactureNumber(String factureNumber) throws unavaiableIdException{
		CommercialInvoice newCommercialInvoice = firstInvoice;
		boolean centinel = false;
		while(newCommercialInvoice != null && centinel == false) {
			if(newCommercialInvoice.getFactureNumber().equals(factureNumber)) {
				centinel = true;
				throw new unavaiableIdException("Error!");	
			}
			newCommercialInvoice = newCommercialInvoice.getNext();
		}	
	}
	public void organizeListWithBubble() {
		CommercialInvoice newPrevius = null;
		CommercialInvoice newNext = null;
		CommercialInvoice oToOrganize = firstInvoice;
				while(oToOrganize != null ){
				newNext = oToOrganize.getNext();
				newPrevius = oToOrganize.getPrevius();
				if(newPrevius != null && newNext != null) {
					if(newPrevius.getFactureNumber().compareTo(newNext.getFactureNumber())>0) {
						firstInvoice = oToOrganize;
						oToOrganize.getPrevius().setNext(oToOrganize.getNext());
						oToOrganize.getPrevius().setPrevius(oToOrganize);
						oToOrganize.getNext().setPrevius(oToOrganize.getPrevius());
						oToOrganize.setNext(oToOrganize.getPrevius());	
					}
				}
				else if(newPrevius == null && newNext != null) {
					if(oToOrganize.getFactureNumber().compareTo(newNext.getFactureNumber())>0) {
						firstInvoice = newNext;
						newNext.getPrevius().setNext(newNext.getNext());
						newNext.getPrevius().setPrevius(newNext);
						newNext.getNext().setPrevius(newNext.getPrevius());
						newNext.setNext(newNext.getPrevius());
					}
				}
			oToOrganize = oToOrganize.getNext();
			}
		}
	@Override
	public void updateInformartionInvoice(CommercialInvoice toUpdate, String date, double totalPrice,
		String paymentType, String factureNumber) {
		toUpdate.setDate(date);
		toUpdate.setTotalPrice(totalPrice);
		toUpdate.setPaymentType(paymentType);
		toUpdate.setFactureNumber(factureNumber);
	}
	private void addWeightProductsToTheInvoice(ArrayList<Product> products, String id) {
		WeightProduct weight = superMarket.searchWeightProduct(id);
		products.add(weight);
	}
	private void addUnitytProductsToTheInvoice(ArrayList<Product> products, String id) {
		UnityProduct unity = superMarket.searchUnityProduct(id);
		products.add(unity);
	}
}
