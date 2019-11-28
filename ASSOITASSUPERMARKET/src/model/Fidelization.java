package model;

import java.io.Serializable;

import exceptions.noMatchesException;
import exceptions.repeatedCustomerException;
import exceptions.thereAreNoRecordsException;
import logicInterfaces.ClientUpdater;

public class Fidelization implements ClientUpdater, Serializable{
	private LoyalClient rootLoyal;
	private CurrentClient rootCurrent;
	public Fidelization() {
		
	}
	/**
	 * Description: Permite obtener la raiz dl arbl de los clientes de tipo leal
	 * @return raiz del arbol
	 */
	public LoyalClient getRootLoyal() {
		return rootLoyal;
	}
	/**
	 * Description: Permite modificar el calor de la raiz del arbol de clientes leales
	 * @param rootLoyal nuevo cliete leal que quiero agregar como raiz
	 */
	public void setRootLoyal(LoyalClient rootLoyal) { 
		this.rootLoyal = rootLoyal;
	}
	/**
	 * Description: Permite obtener la raiz del arbol de los clientes de tipo corriente
	 * @return raiz del arbol
	 */
	public CurrentClient getRootCurrent() {
		return rootCurrent;
	}
	/**
	 * Description: Permite modificar el calor de la raiz del arbol de clientes corrientes
	 * @param rootLoyal nuevo cliete corriente que quiero agregar como raiz
	 */
	public void setRootCurrent(CurrentClient rootCurrent) {
		this.rootCurrent = rootCurrent;
	}
	//---------------------------------------------------> LOYAL CLIENT 
	/**
	 * Description: Permite insertar y crear un nuevo cliente de tipo leal al arbol ABB de clientes leales
	 * @param id Id del cliente leal que quiero agregar 
	 * @param name nombre del cliente leal que quiero agregar 
	 * @param age edad del cliente leal que quiero agregar 
	 * @param email correo del cliente leal que quiero agregar 
	 * @param points puntos acumulados del cliente leal que quiero agregar 
	 * @param discountPercent porcentaje de descuento por se cliente fiel del cliente leal que quiero agregar 
	 * @param dueCard informacion de uso o no de la tarjeta del cliente leal que quiero agregar 
	 * @throws repeatedCustomerException
	 */
	public void insertLoyalClient(String id, String name, String age, String email, int points, double discountPercent, String dueCard) throws repeatedCustomerException {
		LoyalClient newLoyalClient = new LoyalClient( id,  name,  age,  email,  points,  discountPercent, dueCard);
		if(rootLoyal == null) {
			rootLoyal = newLoyalClient;
		} else {
			insertLoyalClient(rootLoyal, newLoyalClient); 
		}
	}
	/**
	 * Description: Dado el caso de que la raiz no sea nula entrara a este metodo recursivo para buscar el lugar indicado para agrgar el nuevo cliente leal al Arbol ABB
	 * @param currentRoot Raiz actual del arbol abb
	 * @param newLoyalClient Objeto de tipo cliente leal a insertar 
	 * @throws repeatedCustomerException si el cliente que se quiere registar tiene una id iguama la de un cliente aniadido antes lanzara esta exception
	 */
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
	/**
	 * Description: Permite buscar un cliente leal agregado antes al Arbol ABB 
	 * @param id Del cliente que se quiere buscar
	 * @return Objeto de tipo cliente leal encontrado
	 * @throws noMatchesException si la id del cliente buscado no existe 
	 * @throws NullPointerException
	 */
	public LoyalClient searchLoyalClientWithId(String id) throws noMatchesException, NullPointerException {
		LoyalClient msg = null;
		if(rootLoyal != null) {
			if(id.equals(rootLoyal.getId())) {
				msg = rootLoyal;
			} else {
				msg = searchLoyalClientWithId(rootLoyal, id);
			}
		} else {
			throw new NullPointerException("Big Failure");
		}
		return msg;
	}
	/**
	 * Description:Dado el caso que la raiz principal no coincida con la id se accede a este metodo para recorrer el arbol 
	 * @param currentRoot Raiz donde se encuetra posicionada
	 * @param id Id de la raiz buscada
	 * @return El cliente leal buscado
	 * @throws noMatchesException Si no se encuentra el cliente
	 */
	private LoyalClient searchLoyalClientWithId(LoyalClient currentRoot, String id) throws noMatchesException {
		LoyalClient msg = null;
		if(currentRoot.getId().compareTo(id)<0) {
			if(currentRoot.getRight() != null) {
				if(currentRoot.getRight().getId().equals(id)) {
					msg = currentRoot.getRight()/*.toString()*/;
				} else {
					msg = searchLoyalClientWithId(currentRoot.getRight(), id);
				}
			} else {
				throw new noMatchesException("Failure! Check!");
			}
		}else {
			if(currentRoot.getLeft() != null) {
				if(currentRoot.getLeft().getId().equals(id)) {
					msg = currentRoot.getLeft()/*.toString()*/;
				} else {
					msg = searchLoyalClientWithId(currentRoot.getLeft(), id);
				} 
			} else {
				throw new noMatchesException("Failure! Check!");
			}
		}
		return msg;
	}
	/**
	 * Description: Permite actaulizar un cliente leal con su id
	 * @param id nuevo Id del cliente
	 * @param name nuevo nombre del cliente
	 * @param age nueva edad del cliente
	 * @param email nuevo correo del cliente
	 * @param points nuevos puntos del cliente
	 * @param discountPercent nuevo percentaje de descuento del cliente
	 * @param dueCard nuevo banco del cliente del cliente
	 * @throws noMatchesException  Si no se encuentra id
	 * @throws NullPointerException
	 */
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
	/**
	 * Description: Permite actaulizar un cliente leal con su id
	 * @param id nuevo Id del cliente
	 * @param name nuevo nombre del cliente
	 * @param age nueva edad del cliente
	 * @param email nuevo correo del cliente
	 * @param points nuevos puntos del cliente
	 * @param discountPercent nuevo percentaje de descuento del cliente
	 * @param dueCard nuevo banco del cliente del cliente
	 * @throws noMatchesException  Si no se encuentra id
	 */
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
	/**
	 * Description: Permite setear cada uno de los atributos menos la id de cliente leal
	 */
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
	/**
	 * Description: Permite insertar un clinte corriente a la lista de cliente
	 * @param id Id del cliente corriente nuevo
	 * @param name nuevo nombre del cliente
	 * @param age nueva edad del cliente
	 * @param email nuevo email del cliente
	 * @throws repeatedCustomerException
	 */
	public void insertCurrentClient(String id, String name, String age, String email) throws repeatedCustomerException {
		CurrentClient newCurrentClient = new CurrentClient( id,  name,  age,  email);
		if(rootCurrent == null) {
			rootCurrent = newCurrentClient;
		} else {
			insertCurrentClient(rootCurrent, newCurrentClient);
		}
	}
	/**
	 * Description: Permite insertar un nuevo vliente en la lista de clientes
	 * @param currentRoot Cliente que es raiz actual
	 * @param newCurrentClient Nuev cliente que se quiere insertar
	 * @throws repeatedCustomerException
	 */
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
	/**
	 * Description: Permite buscar el cliente corriente por su id
	 * @param id iD del cliente que se quiere buscar
	 * @return Cliente que le corresponde esa Id
	 * @throws noMatchesException
	 * @throws NullPointerException
	 */
	public CurrentClient searchCurrentClientWithId(String id) throws noMatchesException, NullPointerException {
		CurrentClient msg = null;
		if(rootCurrent != null) {
			if(id.equals(rootCurrent.getId())) {
				msg = rootCurrent/*.toString()*/;
			} else {
				msg = searchCurrentClientWithId(rootCurrent, id);
			}
		} else {
			throw new NullPointerException("Big Failure");
		}
		return msg;
	}
	/**
	 * Description: Permite buscar un cliente, si no se cumple la condicion del metodo anterior se llamaa a este
	 * @param currentRoot Cliente donde se posciona la raiz actual
	 * @param id Id del cliente que se esta buscando
	 * @return El cliente corriente si en algun momento la id coincide
	 * @throws noMatchesException
	 */
	private CurrentClient searchCurrentClientWithId(CurrentClient currentRoot, String id) throws noMatchesException {
		CurrentClient msg = null;
		if(currentRoot.getId().compareTo(id)<0) {
			if(currentRoot.getRight() != null) {
				if(currentRoot.getRight().getId().equals(id)) {
					msg = currentRoot.getRight()/*.toString()*/;
				} else {
					msg = searchCurrentClientWithId(currentRoot.getRight(), id);
				}
			} else {
				throw new noMatchesException("Failure! Check!");
			}
		}else {
			if(currentRoot.getLeft() != null) {
				if(currentRoot.getLeft().getId().equals(id)) { 
					msg = currentRoot.getLeft()/*.toString()*/;
				} else {
					msg = searchCurrentClientWithId(currentRoot.getLeft(), id);
				} 
			} else {
				throw new noMatchesException("Failure! Check!"); 
			}
		}
		return msg;
	}
	/**
	 * Description: Perimte igresar los nuevos atributos ue se e quieren cambiar a un cliente
	 * @param id Nuevo id
	 * @param name Nuevo nombre
	 * @param age Nueva edad
	 * @param email Buevo correo
	 * @throws noMatchesException
	 * @throws NullPointerException
	 */
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
	/**
	 * Descriotion: Si el metodo anterior no logra actualizar se llama a ete auxiliar
	 * @param currentRoot Cliente corriente donde se encuentra poscionada la raiz
	 * @param id Nueva id
	 * @param name Nuevo nombre
	 * @param age Nueva edad
	 * @param email Nuevo email
	 * @throws noMatchesException
	 */
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
	/**
	 * Description: Permite setaear los alores emialy edad delcliente corrente
	 */
	@Override
	public void updateInformartionCurrent(CurrentClient toUpdate,String name, String age, String email) {
		toUpdate.setName(name);
		toUpdate.setAge(age);
		toUpdate.setEmail(email);
	}
	
	
}
