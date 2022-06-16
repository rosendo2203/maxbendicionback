package com.maxbendicion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxbendicion.model.Producto;
import com.maxbendicion.repository.ProductoRepository;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductoController {
	
	@Autowired
	ProductoRepository productoRepository;
	
	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> getAllProductos(@RequestParam(required = false) String codigoproducto) {
		try {
			List<Producto> productos = new ArrayList<Producto>();

			if (codigoproducto == null) {
				productoRepository.findAll().forEach(productos::add);
			} else {
				productoRepository.findByCodigoproducto(codigoproducto).forEach(productos::add);
			}

			if (productos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(productos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
/////////////////////////////////////////////////////////////////////
	
	@GetMapping("/productos/{codigoproducto}")
	public ResponseEntity<List<Producto>> getProductos(@PathVariable("codigoproducto") String codigoproducto) {
		List<Producto> productos  = productoRepository.findByCodigoproducto(codigoproducto);

		if (productos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
////////////////////////////////////////////////////////////////////////
	@PostMapping("/productos")
	public ResponseEntity<Producto> createProductos(@RequestBody Producto prod) {
		try {
			Producto _producto = productoRepository.save(new Producto(
					prod.getId(),
					prod.getCodigoproducto(),
					prod.getIvacompra(),
					prod.getNitproveedor(),
					prod.getNombreproducto(),
					prod.getPreciocompra(), 
					prod.getPrecioventa()));
			return new ResponseEntity<>(_producto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PutMapping("/productos/{codigoproducto}")
	public ResponseEntity<Producto> updateProductos(@PathVariable("codigoproducto") String codigoproducto,
			@RequestBody Producto prod) {
		Optional<Producto> productoData = productoRepository.findById(codigoproducto);

		if (productoData.isPresent()) {
			Producto _producto = productoData.get();
			_producto.setCodigoproducto(prod.getCodigoproducto());
			_producto.setNombreproducto(prod.getNombreproducto());
			return new ResponseEntity<>(productoRepository.save(_producto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@DeleteMapping("/productos/{codigoproducto}")
	public ResponseEntity<HttpStatus> deleteProdutos(@PathVariable("codigoproducto") String codigoproducto) {
		try {
			productoRepository.deleteById(codigoproducto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/productos")
	public ResponseEntity<HttpStatus> deleteAllProductos() {
		try {
			productoRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
