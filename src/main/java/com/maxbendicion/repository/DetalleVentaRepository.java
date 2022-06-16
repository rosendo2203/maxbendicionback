package com.maxbendicion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maxbendicion.model.DetalleVenta;

public interface DetalleVentaRepository extends MongoRepository<DetalleVenta, String>{
	
	List<DetalleVenta>  findByCodigoproducto(String codigoproducto);
	Optional<DetalleVenta> findById(String id);

}
