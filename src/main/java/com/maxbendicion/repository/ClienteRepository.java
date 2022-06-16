package com.maxbendicion.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maxbendicion.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
	
	List<Cliente>findByNombrecliente(String nombrecliente);
	List<Cliente>findByCedulacliente(Integer cedulacliente);
	
	/*
	List<Cliente>finfByEmailcliente(String emailcliente);
	
	void deleletefindByNombrecliente(String nombrecliente);
	void deleletefindByCedulacliente(long cedulacliente);
	void deleletefindByEmailcliente(long emailcliente);
*/

}
