package rest.api.spring.apispring;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.api.spring.apispring.jpa.Client;
import rest.api.spring.apispring.jpa.ClientRepository;

@SpringBootApplication
@RestController
public class ApiSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSpringApplication.class, args);

	}
	
	@Autowired
	ClientRepository repository;

	@PostMapping("/client")
	public String client(@RequestParam(value = "client") String client, @RequestParam(value = "coffee") String coffee) {

		Client clientSet = repository.save(new Client(client, coffee));

		return "Save! ID Number: " + clientSet.getId();
	}
	
	@GetMapping("/name-search")
	public List<Client> nameSearch(@RequestParam(value = "name") String name) {
		
		return repository.findByFirstName(name);
	
	}
	
	@GetMapping("/coffee-search")
	public List<Client> coffeeSearch(@RequestParam(value = "coffee") String coffee) {
		
		return repository.findByBestCoffee(coffee);
	
	}
	
	
	@GetMapping("/search-by-id")
	public Optional<Client> searchById(@RequestParam(value = "id") Long id) {
		
		return repository.findById(id);
	
	}
	
}
