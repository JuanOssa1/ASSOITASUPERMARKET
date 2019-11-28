package logicInterfaces;

import model.CommercialInvoice;
import model.WeightProduct;

public interface ProductUpdater {
	public void updateInformartionProduct(WeightProduct weight, String id, String name, String bestBefore, double price, String productType, double weigh);
}
