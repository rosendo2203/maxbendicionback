package com.maxbendicion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maxbendicion.model.Producto;



public interface ProductoRepository extends MongoRepository<Producto, String> {
	
	List<Producto>findByCodigoproducto(String codigoproducto);		
	List<Producto> findByNombreproducto(String nombreproducto);
	Optional<Producto> findById(String codigoproducto);

}
