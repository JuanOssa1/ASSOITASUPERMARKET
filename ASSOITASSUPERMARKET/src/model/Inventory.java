package model;

import java.io.Serializable;

import exceptions.insufficientQuantityException;
import exceptions.noMatchesException;
import exceptions.unavaiableIdException;
import logicInterfaces.ProductUpdater;

public class Inventory implements ProductUpdater, Serializable{
	private UnityProduct firstUnity;
	private WeightProduct firstWeight;
	public Inventory() {
		
	}
	/**
	 * gets()*
	 * Description: Permite obtener los valores de los atributos del objeto Inventory
	 * @return
	 */
	public UnityProduct getFirstUnity() {
		return firstUnity;
	}
	/** 
	 * sets()*
	 * Description: Permite modificar los valores de los atributos de Inventory
	 * @param firstUnity
	 */
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
	/**
	 * Description: Permite agregar un producto unity a la lista
	 * @param id
	 * @param name
	 * @param bestBefore
	 * @param price
	 * @param productType
	 * @param quantity
	 * @throws unavaiableIdException
	 */
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
	/**
	 * Description: Permite buscar un producto de tipo unidad con su id
	 * @param id
	 * @return
	 * @throws noMatchesException
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
	/**
	 * Description: Permite validad si existe una id de producto unidad
	 * @param id
	 * @throws noMatchesException
	 */
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
	/**
	 * Description: Permite actualizar la informacion de los productos por unidad 
	 * @param id
	 * @param name
	 * @param bestBefore
	 * @param price
	 * @param productType
	 * @param quantity
	 * @return
	 * @throws noMatchesException
	 */
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
	/**
	 * Description: Permite validar si una ID ya fue usada o no en unityProduct
	 * @param id
	 * @throws unavaiableIdException
	 */
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
	/**
	 * Description: Permite agregar un producto de tipo peso a la lksta de produictos de peso
	 * @param id
	 * @param name
	 * @param bestBefore
	 * @param price
	 * @param productType
	 * @param weight
	 * @throws unavaiableIdException
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
	/**
	 * Description: Permite buscar un producto de tipo peso en la lista de producto de peso con su id
	 * @param id
	 * @return
	 * @throws noMatchesException
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
	/**
	 * Description: Permite validar si existe un producto de tipo peso en la lista de productos de tipo peso
	 * @param id
	 * @throws noMatchesException
	 */
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
	/**
	 * Description: Permite actualiazar los productos de tipo peso
	 * @param id
	 * @param name
	 * @param bestBefore
	 * @param price
	 * @param productType
	 * @param weight
	 * @return
	 * @throws noMatchesException
	 */
	public String updateWeightProductData(String id, String name, String bestBefore, double price, String productType, double weight) throws noMatchesException {
		validateExistenceOfIdWeight(id);
		WeightProduct weightToUpdate = firstWeight;
		String msg = "";
		boolean centinel = false;
		while(weightToUpdate != null && centinel == false) {
			if(weightToUpdate.getId().equals(id)) {
					msg ="El producto libreado actualizado fue"+" "+ weightToUpdate.toString();
					updateInformartionProduct(weightToUpdate, id, name, bestBefore, price, productType, weight);
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
	/**
	 * Description: Permite validar si se puede usar una id ingresada (Si esta repetida)
	 * @param id
	 * @throws unavaiableIdException
	 */
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
	/**
	 * Description: Permite eliminar un producto de unidad la la lista de productos unidad
	 * @param id
	 * @throws noMatchesException
	 */
	public void deleteUnityProduct(String id) throws noMatchesException {
		validateExistenceOfId(id);
        UnityProduct aux = firstUnity;
        boolean cent = false;
        while(!cent && aux!=null) {
            if(aux.getId().equals(id)) {
            	firstUnity = firstUnity.getNext();
                cent = true;
            }else {
                while(!cent && aux.getNext()!=null) {
                    if(aux.getNext().getId().equals(id)) {
                        cent = true;
                        if(aux.getNext().getNext()!=null) {
                            aux.setNext(aux.getNext().getNext());
                            aux.getNext().setPrevius(aux);
                        }else {
                            aux.setNext(null);
                        }
                    }else {
                        aux = aux.getNext();
                    }
                }
            }
        }
    }
	public void deleteWeightProduct(String id) throws noMatchesException {
		validateExistenceOfIdWeight(id);
        WeightProduct aux = firstWeight;
        boolean cent = false;
        while(!cent && aux!=null) {
            if(aux.getId().equals(id)) {
            	firstWeight = firstWeight.getNext();
                cent = true;
            }else {
                while(!cent && aux.getNext()!=null) {
                    if(aux.getNext().getId().equals(id)) {
                        cent = true;
                        if(aux.getNext().getNext()!=null) {
                            aux.setNext(aux.getNext().getNext());
                            aux.getNext().setPrevius(aux);
                        }else {
                            aux.setNext(null);
                        }
                    }else {
                        aux = aux.getNext();
                    }
                }
            }
        }
    }
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
	@Override
	public void updateInformartionProduct(WeightProduct weight, String id, String name, String bestBefore, double price,
			String productType, double weigh) {
		// TODO Auto-generated method stub
		
	}
	
}
