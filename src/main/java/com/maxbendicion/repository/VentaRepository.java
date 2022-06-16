package com.maxbendicion.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maxbendicion.model.Venta;

public interface VentaRepository extends MongoRepository<Venta, String>{

	List<Venta> findByConsecutivo(Integer consecutivo);
	List<Venta> findByCedulacliente(Integer cedulacliente);
}
