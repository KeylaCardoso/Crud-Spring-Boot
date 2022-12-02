package rest.api.spring.apispring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/update")
	public String updateClient(@RequestParam(value = "id") Long id, @RequestParam(value = "coffee") String coffee) {

		Client clientUpdated = service.updateClient(id, coffee);
		
		service.save(clientUpdated);

		return "Atualizado para " + coffee + "! ID Number: " + id;
	}
	
	@GetMapping("/name-search")
	public List<Client> nameSearch(@RequestParam(value = "name") String name) {
		
		return service.findByFirstName(name);
	
	}
	
	@GetMapping("/coffee-search")
	public List<Client> coffeeSearch(@RequestParam(value = "coffee") String coffee) {
		
		return service.findByBestCoffee(coffee);
	
	}
	
	
	@GetMapping("/search-by-id")
	public Optional<Client> searchById(@RequestParam(value = "id") Long id) {
		
		return service.findById(id);
	
	}
	
	@GetMapping("/read-all")
	public Iterable<Client> readAll() {
		
		return service.findAll();
	
	}

}
