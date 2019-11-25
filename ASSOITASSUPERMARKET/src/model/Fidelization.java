package model;

import exceptions.noMatchesException;
import exceptions.repeatedCustomerException;

public class Fidelization {
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
	public String searchLoyalClientWithId(String id) {
		String msg = "";
		if(rootLoyal != null) {
			if(id.equals(rootLoyal.getId())) {
				msg = rootLoyal.toString();
			} else {
				msg = searchLoyalClientWithId(rootLoyal, id);
			}
		}
		return msg;
	}
	private String searchLoyalClientWithId(LoyalClient currentRoot, String id) {
		String msg = "";
		if(currentRoot.getId().compareTo(id)<0) {
			if(currentRoot.getRight() != null) {
				if(currentRoot.getRight().getId().equals(id)) {
					msg = currentRoot.getRight().toString();
				} else {
					msg = searchLoyalClientWithId(currentRoot.getRight(), id);
				}
			} else {
				msg = "No existe esa id";
			}
		}else {
			if(currentRoot.getLeft() != null) {
				if(currentRoot.getLeft().getId().equals(id)) {
					msg = currentRoot.getLeft().toString();
				} else {
					msg = searchLoyalClientWithId(currentRoot.getLeft(), id);
				} 
			} else {
				msg = "No existe esa id"; 
			}
		}
		return msg;
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
	public String searchCurrentClientWithId(String id) throws noMatchesException {
		String msg = "";
		if(rootCurrent != null) {
			if(id.equals(rootCurrent.getId())) {
				msg = rootCurrent.toString();
			} else {
				msg = searchCurrentClientWithId(rootCurrent, id);
			}
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
	
}
