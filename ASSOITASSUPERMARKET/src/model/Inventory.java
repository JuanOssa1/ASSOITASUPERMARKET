package model;

import exceptions.insufficientQuantityException;
import exceptions.noMatchesException;
import exceptions.unavaiableIdException;

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
	//-----------------------------------> ADDER OF UNITIES
	public void addUnityProductToTheList(String id, String name, String bestBefore, double price, String productType, int quantity) throws unavaiableIdException {
		validateAvaiablityOfTheId(id);
		validateAvaiablityOfTheIdWeight(id);
		UnityProduct unityProduct = new UnityProduct( id,  name,  bestBefore,  price,  productType,  quantity);
		if(firstUnity == null) {	
			firstUnity = unityProduct;
		}else {
			UnityProduct newReference = firstUnity;
			while(newReference.getNext()!=null) {
				newReference = newReference.getNext();
			}
			newReference.setNext(unityProduct);
			unityProduct.setPrevius(newReference);
		}
	}
	/*
	public String deleteUnityProductOfTheList(String name) throws noCharacterFindedException {
		NarutoCharacter newReference = first;
		NarutoCharacter before = null;
		NarutoCharacter after = null;
		String msg = "";
		//NarutoCharacter temporalCharacter = null;
		while(newReference != null) {
			if(newReference.getName().equals(name)) {
				
				msg = newReference.toString();
				before = newReference.getPrevius();
				after = newReference.getNext();
				if(before != null && after != null) {
					before.setNext(after);
					after.setPrevius(before);
				}
				else if(before == null && after == null) {
					afterAndBeforeAreNull(newReference);
				}
				else if(before != null && after == null) {
					afterAreNull(newReference);
				}
				else if(before == null && after != null) {
					beforeAreNull(newReference);
				}
			}
			newReference = newReference.getNext();
		}
		if(msg == "") {
			throw new noCharacterFindedException("Error!");
		}
		return msg;
	}
	private String afterAndBeforeAreNull(NarutoCharacter nCharacter) {
		NarutoCharacter newReference = nCharacter;
		String msg ="Eliminado:"+" "+newReference.toString();
		newReference = null;
		return msg;
	}
	private String afterAreNull(NarutoCharacter nCharacter) {
		NarutoCharacter newReference = nCharacter;
		String msg = "Eliminado:"+ " "+newReference.toString();
		NarutoCharacter newReferenceCharacter = newReference.getPrevius();
		first = newReferenceCharacter;
		first.setNext(null);
		return msg;
	}
	private String beforeAreNull(NarutoCharacter nCharacter) {
		NarutoCharacter newReference = nCharacter;
		String msg = "Eliminado:"+ " "+newReference.toString();
		NarutoCharacter newReferenceCharacter = newReference.getNext();
		first = newReferenceCharacter;
		first.setPrevius(null);
		return msg;
	}
	*/
	public UnityProduct searchUnityProduct(String id) throws noMatchesException {
		validateExistenceOfId(id);
		UnityProduct newReference = firstUnity;
		UnityProduct msg = null;
		boolean centinel = false;
		while(newReference != null && centinel == false) {
			if(newReference.getId().equals(id)) {
				msg = newReference;
				centinel = true;
			}
			newReference = newReference.getNext();
		}
		return msg;
	}
	private void validateExistenceOfId(String id) throws noMatchesException {
		UnityProduct newReference = firstUnity;
		String msg = "";
		boolean centinel = false;
		while(newReference != null && centinel == false) {
			if(newReference.getId().equals(id)) {
				msg = newReference.toString();
				centinel = true;
			}
			newReference = newReference.getNext();
		}
		if(msg == "") {
			throw new noMatchesException("Fatal failure! Check!");
		}
	}
	public String updateUnityProductData(String id, String name, String bestBefore, double price, String productType, int quantity) throws noMatchesException {
		validateExistenceOfId(id);
		UnityProduct unityToUpdate = firstUnity;
		String msg = "";
		boolean centinel = false;
		while(unityToUpdate != null && centinel == false) {
			if(unityToUpdate.getId().equals(id)) {
					msg ="El personaje actualizado fue:"+" "+ unityToUpdate.toString();
					unityToUpdate.setId(id);
					unityToUpdate.setName(name);
					unityToUpdate.setBestBefore(bestBefore);
					unityToUpdate.setPrice(price);
					unityToUpdate.setProductType(productType);
					unityToUpdate.setQuantity(quantity);
					centinel = true;
			}
			unityToUpdate = unityToUpdate.getNext();
		}
		return msg;
	}
	private void validateAvaiablityOfTheId(String id) throws unavaiableIdException{
		UnityProduct newReference = firstUnity;
		boolean centinel = false;
		while(newReference != null && centinel == false) {
			if(newReference.getId().equals(id)) {
				centinel = true;
				throw new unavaiableIdException("Error!");	
			}
			newReference = newReference.getNext();
		}	
	}
	/*
	public void organizeListWithBubble() {
		UnityProduct newPrevius = null;
		UnityProduct newNext = null;
		UnityProduct oToOrganize = firstUnity;
				while(oToOrganize != null ){
				newNext = oToOrganize.getNext();
				newPrevius = oToOrganize.getPrevius();
				if(newPrevius != null && newNext != null) {
					if(newPrevius.getId().compareTo(newNext.getId())>0) {
						firstUnity = oToOrganize;
						oToOrganize.getPrevius().setNext(oToOrganize.getNext());
						oToOrganize.getPrevius().setPrevius(oToOrganize);
						oToOrganize.getNext().setPrevius(oToOrganize.getPrevius());
						oToOrganize.setNext(oToOrganize.getPrevius());	
					}
				}
				else if(newPrevius == null && newNext != null) {
					if(oToOrganize.getId().compareTo(newNext.getId())>0) {
						firstUnity = newNext;
						newNext.getPrevius().setNext(newNext.getNext());
						newNext.getPrevius().setPrevius(newNext);
						newNext.getNext().setPrevius(newNext.getPrevius());
						newNext.setNext(newNext.getPrevius());
					}
				}
			oToOrganize = oToOrganize.getNext();
			}
		}
	*/
	//-----------------------------------> ADDER OF WEIGHT
	public void addWeightProductToTheList(String id, String name, String bestBefore, double price, String productType, double weight) throws unavaiableIdException {
		validateAvaiablityOfTheId(id);
		validateAvaiablityOfTheIdWeight(id);
		WeightProduct weightProduct = new WeightProduct( id,  name,  bestBefore,  price,  productType,  weight);
		if(firstWeight == null) {	
			firstWeight = weightProduct;
		}else {
			WeightProduct newReference = firstWeight;
			while(newReference.getNext()!=null) {
				newReference = newReference.getNext();
			}
			newReference.setNext(weightProduct);
			weightProduct.setPrevius(newReference);
		}
	}
	/*
	public String deleteUnityProductOfTheList(String name) throws noCharacterFindedException {
		NarutoCharacter newReference = first;
		NarutoCharacter before = null;
		NarutoCharacter after = null;
		String msg = "";
		//NarutoCharacter temporalCharacter = null;
		while(newReference != null) {
			if(newReference.getName().equals(name)) {
				
				msg = newReference.toString();
				before = newReference.getPrevius();
				after = newReference.getNext();
				if(before != null && after != null) {
					before.setNext(after);
					after.setPrevius(before);
				}
				else if(before == null && after == null) {
					afterAndBeforeAreNull(newReference);
				}
				else if(before != null && after == null) {
					afterAreNull(newReference);
				}
				else if(before == null && after != null) {
					beforeAreNull(newReference);
				}
			}
			newReference = newReference.getNext();
		}
		if(msg == "") {
			throw new noCharacterFindedException("Error!");
		}
		return msg;
	}
	private String afterAndBeforeAreNull(NarutoCharacter nCharacter) {
		NarutoCharacter newReference = nCharacter;
		String msg ="Eliminado:"+" "+newReference.toString();
		newReference = null;
		return msg;
	}
	private String afterAreNull(NarutoCharacter nCharacter) {
		NarutoCharacter newReference = nCharacter;
		String msg = "Eliminado:"+ " "+newReference.toString();
		NarutoCharacter newReferenceCharacter = newReference.getPrevius();
		first = newReferenceCharacter;
		first.setNext(null);
		return msg;
	}
	private String beforeAreNull(NarutoCharacter nCharacter) {
		NarutoCharacter newReference = nCharacter;
		String msg = "Eliminado:"+ " "+newReference.toString();
		NarutoCharacter newReferenceCharacter = newReference.getNext();
		first = newReferenceCharacter;
		first.setPrevius(null);
		return msg;
	}
	*/
	public WeightProduct searchWeightProduct(String id) throws noMatchesException {
		validateExistenceOfIdWeight(id);
		WeightProduct newReference = firstWeight;
		WeightProduct msg = null;
		boolean centinel = false;
		while(newReference != null && centinel == false) {
			if(newReference.getId().equals(id)) {
				msg = newReference;
				centinel = true;
			}
			newReference = newReference.getNext();
		}
		return msg;
	}
	private void validateExistenceOfIdWeight(String id) throws noMatchesException {
		WeightProduct newReference = firstWeight;
		String msg = "";
		boolean centinel = false;
		while(newReference != null && centinel == false) {
			if(newReference.getId().equals(id)) {
				msg = newReference.toString();
				centinel = true;
			}
			newReference = newReference.getNext();
		}
		if(msg == "") {
			throw new noMatchesException("Fatal failure! Check!");
		}
	}
	public String updateWeightProductData(String id, String name, String bestBefore, double price, String productType, double weight) throws noMatchesException {
		validateExistenceOfIdWeight(id);
		WeightProduct weightToUpdate = firstWeight;
		String msg = "";
		boolean centinel = false;
		while(weightToUpdate != null && centinel == false) {
			if(weightToUpdate.getId().equals(id)) {
					msg ="El producto libreado actualizado fue"+" "+ weightToUpdate.toString();
					weightToUpdate.setId(id);
					weightToUpdate.setName(name);
					weightToUpdate.setBestBefore(bestBefore);
					weightToUpdate.setPrice(price);
					weightToUpdate.setProductType(productType);
					weightToUpdate.setWeight(weight);
					centinel = true;
			}
			weightToUpdate = weightToUpdate.getNext();
		}
		return msg;
	}
	private void validateAvaiablityOfTheIdWeight(String id) throws unavaiableIdException{
		WeightProduct newReference = firstWeight;
		boolean centinel = false;
		while(newReference != null && centinel == false) {
			if(newReference.getId().equals(id)) {
				centinel = true;
				throw new unavaiableIdException("Error!");	
			}
			newReference = newReference.getNext();
		}	
	}
	/*
	public void organizeListWithBubbleWeight() {
		WeightProduct newPrevius = null;
		WeightProduct newNext = null;
		WeightProduct oToOrganize = firstWeight;
				while(oToOrganize != null ){
				newNext = oToOrganize.getNext();
				newPrevius = oToOrganize.getPrevius();
				if(newPrevius != null && newNext != null) {
					if(newPrevius.getId().compareTo(newNext.getId())>0) {
						firstWeight = oToOrganize;
						oToOrganize.getPrevius().setNext(oToOrganize.getNext());
						oToOrganize.getPrevius().setPrevius(oToOrganize);
						oToOrganize.getNext().setPrevius(oToOrganize.getPrevius());
						oToOrganize.setNext(oToOrganize.getPrevius());	
					}
				}
				else if(newPrevius == null && newNext != null) {
					if(oToOrganize.getId().compareTo(newNext.getId())>0) {
						firstWeight = newNext;
						newNext.getPrevius().setNext(newNext.getNext());
						newNext.getPrevius().setPrevius(newNext);
						newNext.getNext().setPrevius(newNext.getPrevius());
						newNext.setNext(newNext.getPrevius());
					}
				}
			oToOrganize = oToOrganize.getNext();
			}
		}
	/*
	public void updateWeight(String id, double requiredQuantity) throws noMatchesException, insufficientQuantityException {
		WeightProduct toUpdate = searchWeightProduct(id);
		if((toUpdate.getWeight()-requiredQuantity)<0) {
			throw new insufficientQuantityException("Epic Failure");
		} else {
			toUpdate.setWeight(toUpdate.getWeight()-requiredQuantity);
		}
	}
	*/
	public WeightProduct updateWeight(String id, double requiredQuantity) throws noMatchesException, insufficientQuantityException {
		WeightProduct toUpdate = searchWeightProduct(id);
		toUpdate.update(requiredQuantity);
		return toUpdate;
	}
	public UnityProduct updateUnity(String id, int requiredQuantity) throws noMatchesException, insufficientQuantityException {
		UnityProduct toUpdate = searchUnityProduct(id);
		toUpdate.update(requiredQuantity);
		return toUpdate;
	}
}
