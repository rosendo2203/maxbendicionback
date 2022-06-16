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

import com.maxbendicion.model.DetalleVenta;
import com.maxbendicion.repository.DetalleVentaRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DetalleVentaController {
	
	@Autowired
	DetalleVentaRepository dventaRepository;
	
	@GetMapping("/detalleventas")
	public ResponseEntity<List<DetalleVenta>> getAllDetalleVentas(@RequestParam(required = false) String codigoproducto) {
		try {
			List<DetalleVenta> dventas = new ArrayList<DetalleVenta>();

			if (codigoproducto == null) {
				dventaRepository.findAll().forEach(dventas::add);
			} else {
				dventaRepository.findByCodigoproducto(codigoproducto).forEach(dventas::add);
			}

			if (dventas.isEmpty()) {
				// error 204
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
				// error 201
				return new ResponseEntity<>(dventas, HttpStatus.OK);
			} catch (Exception e) {
				// error 500
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

	}

	
	@PostMapping("/detalleventas")
	public ResponseEntity<DetalleVenta> createDetalleventas(@RequestBody DetalleVenta dvent) {
		try {
			DetalleVenta _dventa = dventaRepository.save(new DetalleVenta(	
					dvent.getId(),
					dvent.getCantidadproducto(), 
					dvent.getCodigoproducto(), 
					dvent.getValortotal(), 
					dvent.getValorventa(),
					dvent.getValoriva()));
			// si lo cree guardo en 200
			return new ResponseEntity<>(_dventa, HttpStatus.CREATED);
		} catch (Exception e) {
			// sino lo pude crear error 500
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/detalleventas/{id}")
	public ResponseEntity<DetalleVenta> updateDetalleVentas(@PathVariable("id") String id,
			@RequestBody DetalleVenta dvent) {
		Optional<DetalleVenta> dventaData = dventaRepository.findById(id);

		if (dventaData.isPresent()) {
			DetalleVenta _dventa = dventaData.get();
			_dventa.setCantidadproducto(dvent.getCantidadproducto());
			_dventa.setCodigoproducto(dvent.getCodigoproducto());
			_dventa.setValortotal(dvent.getValortotal());
			_dventa.setValorventa(dvent.getValorventa());
			_dventa.setValoriva(dvent.getValoriva());
			return new ResponseEntity<>(dventaRepository.save(_dventa), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/detalleventas/{id}")
	public ResponseEntity<HttpStatus> deleteDetalleVentas(@PathVariable("id") String id) {
		try {
			dventaRepository.deleteById(id);
			// si lo borre, retorno 200
			System.out.println(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// sino lo borre, retorno 500
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/detalleventas")
	public ResponseEntity<HttpStatus> deleteAllVentass() {
		try {
			dventaRepository.deleteAll();
			// si los borre todos 201
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// sino los borre todos 500
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
