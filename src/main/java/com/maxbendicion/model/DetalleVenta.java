package com.maxbendicion.model;

import org.springframework.data.annotation.Id;

public class DetalleVenta {
	
	@Id
	private String Id;
	private Integer cantidadproducto;
	private String codigoproducto;
	private Double valoriva;
	private Double valortotal;
	private Double valorventa;
	
	public DetalleVenta() {
		// TODO Auto-generated constructor stub
	}

	public DetalleVenta(String id, Integer cantidadproducto, String codigoproducto, Double valoriva, Double valortotal,
			Double valorventa) {
		super();
		Id = id;
		this.cantidadproducto = cantidadproducto;
		this.codigoproducto = codigoproducto;
		this.valoriva = valoriva;
		this.valortotal = valortotal;
		this.valorventa = valorventa;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Integer getCantidadproducto() {
		return cantidadproducto;
	}

	public void setCantidadproducto(Integer cantidadproducto) {
		this.cantidadproducto = cantidadproducto;
	}

	public String getCodigoproducto() {
		return codigoproducto;
	}

	public void setCodigoproducto(String codigoproducto) {
		this.codigoproducto = codigoproducto;
	}

	public Double getValoriva() {
		return valoriva;
	}

	public void setValoriva(Double valoriva) {
		this.valoriva = valoriva;
	}

	public Double getValortotal() {
		return valortotal;
	}

	public void setValortotal(Double valortotal) {
		this.valortotal = valortotal;
	}

	public Double getValorventa() {
		return valorventa;
	}

	public void setValorventa(Double valorventa) {
		this.valorventa = valorventa;
	}

	
}
