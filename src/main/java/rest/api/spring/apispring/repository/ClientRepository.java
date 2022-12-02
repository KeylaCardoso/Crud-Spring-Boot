package rest.api.spring.apispring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import rest.api.spring.apispring.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

	  List<Client> findByFirstName(String firstName);
	  
	  List<Client> findByBestCoffee(String bestCoffee);

	  Client findById(long id);
	  
	  

}
