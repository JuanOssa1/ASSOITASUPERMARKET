package logicInterfaces;

import model.CommercialInvoice;

public interface InvoiceUpdater {
	public void updateInformartionInvoice(CommercialInvoice toUpdate, String date, String paymentType);

}
