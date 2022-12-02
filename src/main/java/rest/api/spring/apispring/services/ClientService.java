package rest.api.spring.apispring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.api.spring.apispring.entities.Client;
import rest.api.spring.apispring.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository repository;	

	public Client save(Client client) {
		return repository.save(client);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	//
	public Client updateClient(Long id, String coffee) {
		
		Optional<Client> clientToUpdate = repository.findById(id);
		
		Client clientUpdated = clientToUpdate.get();
		
		clientUpdated.setBestCoffee(coffee);
		
		return clientUpdated;
	}

	public List<Client> findByFirstName(String name) {
		
		return repository.findByFirstName(name);
	}

	public List<Client> findByBestCoffee(String coffee) {
		
		return repository.findByBestCoffee(coffee);
	}

	public Optional<Client> findById(Long id) {
		
		return repository.findById(id);
	}

	public Iterable<Client> findAll() {
		
		return repository.findAll();
	}

}
