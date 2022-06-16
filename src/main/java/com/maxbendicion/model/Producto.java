package com.maxbendicion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Producto")
public class Producto {
	
	@Id
	private String id;
	private String codigoproducto;
	private Double ivacompra;
	private Integer nitproveedor;
	private String nombreproducto;
	private Double preciocompra;
	private Double precioventa;
	public Producto(String id, String codigoproducto, Double ivacompra, Integer nitproveedor, String nombreproducto,
			Double preciocompra, Double precioventa) {
		super();
		this.id = id;
		this.codigoproducto = codigoproducto;
		this.ivacompra = ivacompra;
		this.nitproveedor = nitproveedor;
		this.nombreproducto = nombreproducto;
		this.preciocompra = preciocompra;
		this.precioventa = precioventa;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodigoproducto() {
		return codigoproducto;
	}
	public void setCodigoproducto(String codigoproducto) {
		this.codigoproducto = codigoproducto;
	}
	public Double getIvacompra() {
		return ivacompra;
	}
	public void setIvacompra(Double ivacompra) {
		this.ivacompra = ivacompra;
	}
	public Integer getNitproveedor() {
		return nitproveedor;
	}
	public void setNitproveedor(Integer nitproveedor) {
		this.nitproveedor = nitproveedor;
	}
	public String getNombreproducto() {
		return nombreproducto;
	}
	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}
	public Double getPreciocompra() {
		return preciocompra;
	}
	public void setPreciocompra(Double preciocompra) {
		this.preciocompra = preciocompra;
	}
	public Double getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(Double precioventa) {
		this.precioventa = precioventa;
	}
	
}
