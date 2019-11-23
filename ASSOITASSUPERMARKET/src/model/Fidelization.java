package model;

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
}
