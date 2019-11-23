package model;

public class Inventory {
	private UnityProduct firstUnity;
	private WeightProduct firstWeight;
	public Inventory() {
		
	}
	public UnityProduct getFirstUnity() {
		return firstUnity;
	}
	public void setFirstUnity(UnityProduct firstUnity) {
		this.firstUnity = firstUnity;
	}
	public WeightProduct getFirstWeight() {
		return firstWeight;
	}
	public void setFirstWeight(WeightProduct firstWeight) {
		this.firstWeight = firstWeight;
	}
	
}
