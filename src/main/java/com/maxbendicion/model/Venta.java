package com.maxbendicion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ventas")
public class Venta {
	
	//atributos en privado para la encapsulacion
	
		@Id
		private String id;
		private Long consecutivo;
		private Integer cedulacliente;
		private String username;	
		private Double ivaventa;
		private Double totalventa;
		private Double valorventa;
		
		public Venta() {
			// TODO Auto-generated constructor stub
		}

		public Venta(String id, Long consecutivo, Integer cedulacliente, String username, Double ivaventa,
				Double totalventa, Double valorventa) {
			super();
			this.id = id;
			this.consecutivo = consecutivo;
			this.cedulacliente = cedulacliente;
			this.username = username;
			this.ivaventa = ivaventa;
			this.totalventa = totalventa;
			this.valorventa = valorventa;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Long getConsecutivo() {
			return consecutivo;
		}

		public void setConsecutivo(Long consecutivo) {
			this.consecutivo = consecutivo;
		}

		public Integer getCedulacliente() {
			return cedulacliente;
		}

		public void setCedulacliente(Integer cedulacliente) {
			this.cedulacliente = cedulacliente;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Double getIvaventa() {
			return ivaventa;
		}

		public void setIvaventa(Double ivaventa) {
			this.ivaventa = ivaventa;
		}

		public Double getTotalventa() {
			return totalventa;
		}

		public void setTotalventa(Double totalventa) {
			this.totalventa = totalventa;
		}

		public Double getValorventa() {
			return valorventa;
		}

		public void setValorventa(Double valorventa) {
			this.valorventa = valorventa;
		}

	
}
