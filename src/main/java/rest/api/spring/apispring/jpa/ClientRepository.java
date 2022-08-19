package rest.api.spring.apispring.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

	  List<Client> findByFirstName(String firstName);
	  
	  List<Client> findByBestCoffee(String bestCoffee);

	  Client findById(long id);

}
