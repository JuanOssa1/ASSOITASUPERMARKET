package model;

import exceptions.noMatchesException;
import exceptions.repeatedCustomerException;
import exceptions.thereAreNoRecordsException;
import logicInterfaces.ClientUpdater;

public class Fidelization implements ClientUpdater{
	private LoyalClient rootLoyal;
	private CurrentClient rootCurrent;
	public Fidelization() {
		
	}
	public LoyalClient getRootLoyal() {
		return rootLoyal;
	}
	public void setRootLoyal(LoyalClient rootLoyal) {
		this.rootLoyal = rootLoyal;
	}
	public CurrentClient getRootCurrent() {
		return rootCurrent;
	}
	public void setRootCurrent(CurrentClient rootCurrent) {
		this.rootCurrent = rootCurrent;
	}
	//---------------------------------------------------> LOYAL CLIENT 
	public void insertLoyalClient(String id, String name, String age, String email, int points, double discountPercent, String dueCard) throws repeatedCustomerException {
		LoyalClient newLoyalClient = new LoyalClient( id,  name,  age,  email,  points,  discountPercent, dueCard);
		if(rootLoyal == null) {
			rootLoyal = newLoyalClient;
		} else {
			insertLoyalClient(rootLoyal, newLoyalClient);
		}
	}
	private void insertLoyalClient(LoyalClient currentRoot, LoyalClient newLoyalClient) throws repeatedCustomerException {
		if(currentRoot.getId().compareTo(newLoyalClient.getId())!=0) {
			if(currentRoot.getId().compareTo(newLoyalClient.getId())>0) {
				if(currentRoot.getLeft() == null) {
					currentRoot.setLeft(newLoyalClient);
				} else {
					insertLoyalClient(currentRoot.getLeft(), newLoyalClient);
				}
			} else {
				if(currentRoot.getRight() == null) {
					currentRoot.setRight(newLoyalClient);
				} else {
					insertLoyalClient(currentRoot.getRight(), newLoyalClient);
				}
			}
		} else {
			throw new repeatedCustomerException("Falla!");
		}
	}
	public String searchLoyalClientWithId(String id) throws noMatchesException, NullPointerException {
		String msg = "";
		if(rootLoyal != null) {
			if(id.equals(rootLoyal.getId())) {
				msg = rootLoyal.toString();
			} else {
				msg = searchLoyalClientWithId(rootLoyal, id);
			}
		} else {
			throw new NullPointerException("Big Failure");
		}
		return msg;
	}
	private String searchLoyalClientWithId(LoyalClient currentRoot, String id) throws noMatchesException {
		String msg = "";
		if(currentRoot.getId().compareTo(id)<0) {
			if(currentRoot.getRight() != null) {
				if(currentRoot.getRight().getId().equals(id)) {
					msg = currentRoot.getRight().toString();
				} else {
					msg = searchLoyalClientWithId(currentRoot.getRight(), id);
				}
			} else {
				throw new noMatchesException("Failure! Check!");
			}
		}else {
			if(currentRoot.getLeft() != null) {
				if(currentRoot.getLeft().getId().equals(id)) {
					msg = currentRoot.getLeft().toString();
				} else {
					msg = searchLoyalClientWithId(currentRoot.getLeft(), id);
				} 
			} else {
				throw new noMatchesException("Failure! Check!");
			}
		}
		return msg;
	}
	
	
	public void updateLoyalClientWithId(String id, String name, String age, String email, int points, double discountPercent, String dueCard) throws noMatchesException, NullPointerException {
		if(rootLoyal != null) {
			if(id.equals(rootLoyal.getId())) {
				updateInformartionLoyal(rootLoyal, name, age, email, points, discountPercent, dueCard);
			} else {
				updateLoyalClientWithId(rootLoyal, id, name, age, email, points, discountPercent, dueCard);
			}
		} else {
			throw new NullPointerException("No info");
		}
	}
	private void updateLoyalClientWithId(LoyalClient currentRoot, String id, String name, String age, String email, int points, double discountPercent, String dueCard) throws noMatchesException {
		if(currentRoot.getId().compareTo(id)<0) {
			if(currentRoot.getRight() != null) {
				if(currentRoot.getRight().getId().equals(id)) {
					updateInformartionLoyal(currentRoot.getRight(), name, age, email, points, discountPercent, dueCard);
				} else {
					updateLoyalClientWithId(currentRoot.getRight(), id, name, age, email, points, discountPercent, dueCard);
				}
			} else {
				throw new noMatchesException("FAILURE!!!! CHECK!!");
			}
		}else {
			if(currentRoot.getLeft() != null) {
				if(currentRoot.getLeft().getId().equals(id)) {
					updateInformartionLoyal(currentRoot.getLeft(), name, age, email, points, discountPercent, dueCard);
				} else {
					searchLoyalClientWithId(currentRoot.getLeft(), id);
				} 
			} else {
				throw new noMatchesException("FAILURE!!!! CHECK!!");
			}
		}
	}
	@Override
	public void updateInformartionLoyal(LoyalClient toUpdate, String name, String age, String email, int points, double discountPercent, String dueCard) {
		toUpdate.setName(name);
		toUpdate.setAge(age);
		toUpdate.setEmail(email);
		toUpdate.setPoints(points);
		toUpdate.setDiscountPercent(discountPercent);
		toUpdate.setDueCard(dueCard);
	}
	
	//---------------------------------------------------> CURRENT CLIENT 
	public void insertCurrentClient(String id, String name, String age, String email) throws repeatedCustomerException {
		CurrentClient newCurrentClient = new CurrentClient( id,  name,  age,  email);
		if(rootCurrent == null) {
			rootCurrent = newCurrentClient;
		} else {
			insertCurrentClient(rootCurrent, newCurrentClient);
		}
	}
	private void insertCurrentClient(CurrentClient currentRoot, CurrentClient newCurrentClient) throws repeatedCustomerException {
		if(currentRoot.getId().compareTo(newCurrentClient.getId())!=0) {
			if(currentRoot.getId().compareTo(newCurrentClient.getId())>0) {
				if(currentRoot.getLeft() == null) {
					currentRoot.setLeft(newCurrentClient);
				} else {
					insertCurrentClient(currentRoot.getLeft(), newCurrentClient);
				}
			} else {
				if(currentRoot.getRight() == null) {
					currentRoot.setRight(newCurrentClient);
				} else {
					insertCurrentClient(currentRoot.getRight(), newCurrentClient);
				}
			}
		} else {
			throw new repeatedCustomerException("Falla!");
		}
	}
	public String searchCurrentClientWithId(String id) throws noMatchesException, NullPointerException {
		String msg = "";
		if(rootCurrent != null) {
			if(id.equals(rootCurrent.getId())) {
				msg = rootCurrent.toString();
			} else {
				msg = searchCurrentClientWithId(rootCurrent, id);
			}
		} else {
			throw new NullPointerException("Big Failure");
		}
		return msg;
	}
	private String searchCurrentClientWithId(CurrentClient currentRoot, String id) throws noMatchesException {
		String msg = "";
		if(currentRoot.getId().compareTo(id)<0) {
			if(currentRoot.getRight() != null) {
				if(currentRoot.getRight().getId().equals(id)) {
					msg = currentRoot.getRight().toString();
				} else {
					msg = searchCurrentClientWithId(currentRoot.getRight(), id);
				}
			} else {
				throw new noMatchesException("Failure! Check!");
			}
		}else {
			if(currentRoot.getLeft() != null) {
				if(currentRoot.getLeft().getId().equals(id)) {
					msg = currentRoot.getLeft().toString();
				} else {
					msg = searchCurrentClientWithId(currentRoot.getLeft(), id);
				} 
			} else {
				throw new noMatchesException("Failure! Check!"); 
			}
		}
		return msg;
	}
	public void updateCurrentClientWithId(String id, String name, String age, String email) throws noMatchesException, NullPointerException {
		if(rootCurrent != null) {
			if(id.equals(rootCurrent.getId())) {
				updateInformartionCurrent(rootCurrent, name, age, email);
			} else {
				updateCurrentClientWithId(rootCurrent, id, name, age, email);
			}
		} else {
			throw new NullPointerException("No info");
		}
	}
	private void updateCurrentClientWithId(CurrentClient currentRoot, String id, String name, String age, String email) throws noMatchesException {
		if(currentRoot.getId().compareTo(id)<0) {
			if(currentRoot.getRight() != null) {
				if(currentRoot.getRight().getId().equals(id)) {
					updateInformartionCurrent(currentRoot.getRight(), name, age, email);
				} else {
					updateCurrentClientWithId(currentRoot.getRight(), id, name, age, email);
				}
			} else {
				throw new noMatchesException("FAILURE!!!! CHECK!!");
			}
		}else {
			if(currentRoot.getLeft() != null) {
				if(currentRoot.getLeft().getId().equals(id)) {
					updateInformartionCurrent(currentRoot.getLeft(), name, age, email);
				} else {
					updateCurrentClientWithId(currentRoot.getLeft(), id, name, age, email);
				} 
			} else {
				throw new noMatchesException("FAILURE!!!! CHECK!!");
			}
		}
	}
	@Override
	public void updateInformartionCurrent(CurrentClient toUpdate,String name, String age, String email) {
		toUpdate.setName(name);
		toUpdate.setAge(age);
		toUpdate.setEmail(email);
	}
	
	
}
