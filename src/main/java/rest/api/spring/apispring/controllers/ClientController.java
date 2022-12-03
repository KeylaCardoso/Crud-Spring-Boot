package rest.api.spring.apispring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import rest.api.spring.apispring.entities.Client;
import rest.api.spring.apispring.services.ClientService;

@RestController
public class ClientController {
	
	@Autowired
	ClientService service;

	@PostMapping("/client")
	public String client(@RequestParam(value = "client") String client, @RequestParam(value = "coffee") String coffee) {
		
		Client clientSet = service.save(new Client(client, coffee));

		return "Save! ID Number: " + clientSet.getId();
	}
	
	@DeleteMapping("/delete-client")
	public String deleteClient(@RequestParam(value = "id") Long id) {

		service.deleteById(id);

		return "Deletado! ID Number: " + id;
	}
	
	@PutMapping("/update")
	public String updateClient(@RequestParam(value = "id") Long id, @RequestParam(value = "coffee") String coffee) {

		Client clientUpdated = service.updateClient(id, coffee);
		
		service.save(clientUpdated);

		return "Atualizado para " + coffee + "! ID Number: " + id;
	}
	
	@GetMapping("/name-search")
	public ResponseEntity nameSearch(@RequestParam(value = "name") String name) {
		
		try {
			
			List<Client> clientByName = service.findByFirstName(name);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(clientByName);
			
		} catch (ResponseStatusException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		}
		
		
	
	}
	
	@GetMapping("/coffee-search")
	public ResponseEntity coffeeSearch(@RequestParam(value = "coffee") String coffee) {
		
		try {
			
			List<Client> clientByBestCoffee = service.findByBestCoffee(coffee);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(clientByBestCoffee);
			
		} catch (ResponseStatusException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		}
		
		
	
	}
	
	
	@GetMapping("/search-by-id")
	public ResponseEntity searchById(@RequestParam(value = "id") Long id) {
		
		try {
			
			Client clientById = service.findById(id);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(clientById);
			
		} catch (ResponseStatusException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
			
		}
		
		
	
	}
	
	@GetMapping("/read-all")
	public Iterable<Client> readAll() {
		
		return service.findAll();
	
	}

}
