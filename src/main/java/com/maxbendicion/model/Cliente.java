package com.maxbendicion.model;

import org.springframework.data.annotation.Id;

public class Cliente {
	
	@Id
	private String id;
	private Integer cedulacliente;
	private String direccioncliente;
	private String emailcliente;
	private String nombrecliente;
	private String telefonocliente;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer cedulacliente, String direccioncliente, String emailcliente, String nombrecliente,
			String telefonocliente) {
		super();
		this.cedulacliente = cedulacliente;
		this.direccioncliente = direccioncliente;
		this.emailcliente = emailcliente;
		this.nombrecliente = nombrecliente;
		this.telefonocliente = telefonocliente;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCedulacliente() {
		return cedulacliente;
	}

	public void setCedulacliente(Integer cedulacliente) {
		this.cedulacliente = cedulacliente;
	}

	public String getDireccioncliente() {
		return direccioncliente;
	}

	public void setDireccioncliente(String direccioncliente) {
		this.direccioncliente = direccioncliente;
	}

	public String getEmailcliente() {
		return emailcliente;
	}

	public void setEmailcliente(String emailcliente) {
		this.emailcliente = emailcliente;
	}

	public String getNombrecliente() {
		return nombrecliente;
	}

	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	public String getTelefonocliente() {
		return telefonocliente;
	}

	public void setTelefonocliente(String telefonocliente) {
		this.telefonocliente = telefonocliente;
	}
	

}
