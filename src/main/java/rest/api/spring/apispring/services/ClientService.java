package rest.api.spring.apispring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rest.api.spring.apispring.entities.Client;
import rest.api.spring.apispring.repository.ClientRepository;

@Service
public class ClientService {
	
	String MENSAGEM_NOT_FOUND = "Cliente não encontrado";
	String MENSAGEM_COFFEE_NOT_FOUND = "Não há clientes com essa preferência de café";
	String MENSAGEM_NAME_NOT_FOUND = "Não há clientes com esse nome";		
	
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
		
		List<Client> clientByName = repository.findByFirstName(name);
		
		if(clientByName.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
			   		MENSAGEM_NAME_NOT_FOUND);
		}
		
		return clientByName;
		
	}
	

	public List<Client> findByBestCoffee(String coffee) {
		
		List<Client> clientByBestCoffee = repository.findByBestCoffee(coffee);
		
		if(clientByBestCoffee.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
			   		MENSAGEM_COFFEE_NOT_FOUND);
		}
		
		return clientByBestCoffee;
	}
	

	public Client findById(Long id) {
		
		Optional<Client> clientById = repository.findById(id);
		
		if(clientById.isEmpty()) {
			   throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
					   		MENSAGEM_NOT_FOUND);
		}
		
		return clientById.get();
	}

	public Iterable<Client> findAll() {
		
		return repository.findAll();
	}

}
