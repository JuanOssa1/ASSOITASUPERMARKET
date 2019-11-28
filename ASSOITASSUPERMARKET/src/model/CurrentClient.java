package model;

import java.io.Serializable;

public class CurrentClient extends Client implements Serializable{ 
	
	private CurrentClient left; 
	private CurrentClient right;
	public CurrentClient(String id, String name, String age, String email) {
		super(id, name, age, email);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Description: Permite obtener el cliente corriente que se encuentra a lado izquierdo del cliente raiz actual del arbol ABB ordenado por ID
	 * @return el cliente que se encuentra situado a la izquierda del cliente raiz
	 */
	public CurrentClient getLeft() {
		return left;
	}
	/**
	 * Description: Permite modificar el cliente corriente que se encuentra a lado izquierdo del cliente raiz actual del arbol ABB ordenado por ID
	 * @param El nuevp cliente que se quiere asignar a la zquierda del arbol
	 */
	public void setLeft(CurrentClient left) {
		this.left = left;
	}
	/**
	 * Description: Permite obtener el cliente corriente que se encuentra a lado derecho del cliente raiz actual del arbol ABB ordenado por ID
	 * @return el cliente que se encuentra situado al lado derecho del cliente raiz
	 */
	public CurrentClient getRight() {
		return right;
	} 
	/**
	 * Description: Permite modificar el cliente corriente que se encuentra a lado derecho del cliente raiz actual del arbol ABB ordenado por ID
	 * @param El nuevp cliente que se quiere asignar al lado derecho del arbol
	 */
	public void setRight(CurrentClient right) {
		this.right = right;
	}
	/**
	 * Description: Convierte cada uno de los atributos de la clase currentClient a un objeto de tipo String
	 * @return String con la informacion de cada uno de los atributos
	 */
	@Override
	public String toString() {
		return "CurrentClient [getId()=" + getId() + ", getName()=" + getName() + ", getAge()=" + getAge()
				+ ", getEmail()=" + getEmail() + "]";
	}
	

}
