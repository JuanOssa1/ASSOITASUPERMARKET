package logicInterfaces;

import model.CurrentClient;
import model.LoyalClient;

public interface ClientUpdater {
	public void updateInformartionLoyal(LoyalClient toUpdate, String name, String age, String email,  int points, double discountPercent, String dueCard);
	public void updateInformartionCurrent(CurrentClient toUpdate, String name, String age, String email);
}
