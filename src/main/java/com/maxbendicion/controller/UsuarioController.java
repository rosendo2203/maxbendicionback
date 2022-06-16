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

import com.maxbendicion.model.Usuario;
import com.maxbendicion.repository.UsuarioRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	UsuarioRepository usernameRepository;
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAllUsuarios(@RequestParam(required = false) String username) {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();

			if (username == null) {
				usernameRepository.findAll().forEach(usuarios::add);
			} else {
				usernameRepository.findByUsername(username).forEach(usuarios::add);
			}

			if (usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	////////
	
	//////
//////////
	 @PostMapping("/usuarios")
	  public ResponseEntity<Usuario> createUsuarios(@RequestBody Usuario user) {
		    try {
		      Usuario _usuario = usernameRepository.save(new  Usuario(
		    		  user.getUsername(),
		    		  user.getPassword(),
		    		  user.getNombrecompleto(),
		    		  user.getEmail()
		    		  ));
		      return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		  }
/////////
	  @PutMapping("/usuarios/{id}")
	  public ResponseEntity<Usuario> updateUsuarios(@PathVariable("id") String id, @RequestBody Usuario user) {
	    Optional<Usuario> usuarioData = usernameRepository.findById(id);

	    if (usuarioData.isPresent()) {
	      Usuario _usuario = usuarioData.get();
	      _usuario.setUsername(user.getUsername());
	      _usuario.setPassword(user.getPassword());
	      return new ResponseEntity<>(usernameRepository.save(_usuario), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
///////
	  @DeleteMapping("/usuarios/{id}")
	  public ResponseEntity<HttpStatus> deleteUsuarios(@PathVariable("id") String id) {
	    try {
	    	usernameRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
///////
	  @DeleteMapping("/usuarios")
	  public ResponseEntity<HttpStatus> deleteAllUsuarios() {
	    try {
	    	usernameRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
/////
	  @GetMapping("/usuarios/{username}")
	  public ResponseEntity<List<Usuario>> getUsuarios(@PathVariable("username") String username) {
	    try {
	      List<Usuario> usuarios = usernameRepository.findByUsername(username);

	      if (usuarios.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(usuarios, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
