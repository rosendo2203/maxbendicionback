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

import com.maxbendicion.model.Venta;
import com.maxbendicion.repository.VentaRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class VentaController {

	@Autowired
	VentaRepository ventaRepository;
	
	@GetMapping("/ventas")
	public ResponseEntity<List<Venta>> getAllVentas(@RequestParam(required = false) Integer consecutivo) {

		try {
			List<Venta> ventas = new ArrayList<Venta>();
			if (consecutivo == null) {

				ventaRepository.findAll().forEach(ventas::add);
			} else {
				ventaRepository.findByConsecutivo(consecutivo).forEach(ventas::add);
			}
			if (ventas.isEmpty()) {
				// Error 204
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			// Error 201
			return new ResponseEntity<>(ventas, HttpStatus.OK);

		} catch (Exception e) {
			// Error 500
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/getcedulaventas/{cedulacliente}")
	public ResponseEntity<List<Venta>> getVentasByCedula(@PathVariable("cedulacliente") Integer cedulacliente) {

		try {
			System.out.println(cedulacliente);
			List<Venta> ventas = ventaRepository.findByCedulacliente(cedulacliente);

			if (ventas.isEmpty()) {
				// no encontre info
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			// Encontre info
			return new ResponseEntity<>(ventas, HttpStatus.OK);
		} catch (Exception e) {
			// Error paso algo
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PutMapping("/ventas/{id}")
	public ResponseEntity<Venta> updateVentas(@PathVariable("id") String id, @RequestBody Venta vent) {
		Optional<Venta> ventaData = ventaRepository.findById(id);

		if (ventaData.isPresent()) {
			Venta _venta = ventaData.get();
			_venta.setConsecutivo(vent.getConsecutivo());
			_venta.setCedulacliente(vent.getCedulacliente());
			_venta.setUsername(vent.getUsername());
			_venta.setIvaventa(vent.getIvaventa());
			_venta.setTotalventa(vent.getTotalventa());
			_venta.setValorventa(vent.getValorventa());
			return new ResponseEntity<>(ventaRepository.save(_venta), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@DeleteMapping("/ventas/{id}")
	public ResponseEntity<HttpStatus> deleteVentas(@PathVariable("id") String id) {
		try {
			ventaRepository.deleteById(id);
			// Si se borra retorna 200
			System.out.println(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// Si no lo borra retorna 500
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@DeleteMapping("/ventas")
	public ResponseEntity<HttpStatus> deleteAllVentass() {
		try {
			ventaRepository.deleteAll();
			// Si se borra todo regresa 201
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// Si no se borra todo regresa 500
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@PostMapping("/ventas")
	public ResponseEntity<Venta> createVentas(@RequestBody Venta venta) {
		try {
			Venta _venta = ventaRepository.save(new Venta(
					venta.getId(),
					venta.getConsecutivo(), 
					venta.getCedulacliente(),
					venta.getUsername(), 				
					venta.getIvaventa(), 
					venta.getTotalventa(), 
					venta.getValorventa()
					));
			// si lo cree guardo en 200
			return new ResponseEntity<>(_venta, HttpStatus.CREATED);
		} catch (Exception e) {
			// sino lo pude crear error 500
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	 @GetMapping("/ventas/consecutivo")
	public ResponseEntity<Long> getVentaConsecutivo() {
		try {
			
		ArrayList<Venta> aux = (ArrayList<Venta>) ventaRepository.findAll();
		System.out.println(aux.size());
		long mayor = 10001;
		for (Venta v : aux) {

			System.out.println(v);
			if (v.getConsecutivo() > mayor) {
				mayor = v.getConsecutivo();
			}
		}
	
		if (aux.isEmpty()) {
			mayor=10001;
		}
			return new ResponseEntity<>(mayor + 1, HttpStatus.OK);
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	 
}
