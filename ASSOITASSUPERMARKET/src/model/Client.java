package model;

import java.io.Serializable;

public abstract class Client implements Serializable{
	private String id;
	private String name;
	private String age;
	private String email;
	public Client(String id, String name, String age, String email) {
		super();
		this.id = id; 
		this.name = name;
		this.age = age;
		this.email = email;
	}
	/**
	 * Description: Permite acceder a la id del cliente
	 * @return Id del cliente creado
	 */
	public String getId() {
		return id;
	}
	/**
	 * Description: Permite cambiar la id del cliente 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Description: Permite acceder al nombre del cliente
	 * @return Nombre del cliente creado
	 */
	public String getName() {
		return name;
	}
	/**
	 * Description: Permite cambiar el nombre del cliente
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Description: Permite acceder a la edad del cliente 
	 * @return Edad del cliente
	 */
	public String getAge() {
		return age;
	}
	/**
	 * Decription: Permite cambiar la edad del cliente
	 * @param age
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * Description: Permite acceder al email del cliente
	 * @return Email del cliente
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Description: Permite meodificar el email del cliente a uno diferente 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Description: Retorna un string con cada uno de los atributos de un cliente
	 */
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + "]";
	}
	
	
}
