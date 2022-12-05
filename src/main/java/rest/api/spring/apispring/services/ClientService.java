package rest.api.spring.apispring.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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
	String MENSAGEM_DADOS_CLIENTES_NOT_FOUND = "Dados do cliente inclompletos. Cliente não salvo.";
	String MENSAGEM_ID_BAD_REQUEST = "Id de cliente não digitado ou não numérico";
	String MENSAGEM_ID_NUMERICO_BAD_REQUEST = "Id de cliente inválido, favor digitar um valor numérico a partir de 1";
	String MENSAGEM_COFFEE_BAD_REQUEST = "O tipo de café preferencial a ser alterado para o cliente não foi fornecido,"
			+ " favor inserí-lo na sua requisição para atualizar";
	
	@Autowired
	ClientRepository repository;	

	public Client save(Client client) {
		
		if(client.getFirstName().isEmpty() || client.getBestCoffee().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					MENSAGEM_DADOS_CLIENTES_NOT_FOUND);
		}
		
		Client clientSet = repository.save(client);
		
		return clientSet;
	}

	public void deleteById(String id) { 
		
		validarId(id);
		
		findById(id);
		
		repository.deleteById(Long.valueOf(id));		
		
	}

	private void validarId(String id) {
		if(id == null || !StringUtils.isNumeric(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					MENSAGEM_ID_BAD_REQUEST);
		}
		
		Long idNumerico = Long.valueOf(id);
		
		if(idNumerico <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					MENSAGEM_ID_NUMERICO_BAD_REQUEST);
			
		}
	}

	//
	public Client updateClient(String id, String coffee) {
		
		validarId(id);
		
		if(coffee.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					MENSAGEM_COFFEE_BAD_REQUEST);
		}
		
		Client clientUpdated = findById(id);
		
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
	

	public Client findById(String id) {
		
		validarId(id);
		
		Optional<Client> clientById = repository.findById(Long.valueOf(id));
		
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
