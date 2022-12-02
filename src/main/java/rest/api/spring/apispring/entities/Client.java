package rest.api.spring.apispring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
	
	 @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	  private String firstName;
	  private String bestCoffee;

	

	
	protected Client() {}

	  public Client(String firstName, String bestCoffee) {
	    this.firstName = firstName;
	    this.bestCoffee = bestCoffee;
	  }

	  @Override
	  public String toString() {
	    return String.format(
	        "Customer[id=%d, firstName='%s', bestCoffee='%s']",
	        id, firstName, bestCoffee);
	  }

	  public Long getId() {
	    return id;
	  }

	  public String getFirstName() {
	    return firstName;
	  }

	  public String getBestCoffee() {
	    return bestCoffee;
	  }
	  
	  public void setBestCoffee(String bestCoffee) {
			this.bestCoffee = bestCoffee;
		}

	}

