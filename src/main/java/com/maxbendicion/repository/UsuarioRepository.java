package com.maxbendicion.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maxbendicion.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	
	List<Usuario>findByUsername(String username);
	List<Usuario>findByNombrecompleto(String nombrecompleto);

}
