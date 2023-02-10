package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Copia_fisica {
	@Id
	@GeneratedValue
	private int id;
	private boolean deteriorado;
	
	@OneToOne
	private Copia original;
	
	public Copia_fisica() {
		
	}
	
public boolean isDeteriorado() {
		return deteriorado;
	}

	public void setDeteriorado(boolean deteriorado) {
		this.deteriorado = deteriorado;
	}

public Copia_fisica(boolean deteriorado) {
		this.deteriorado = deteriorado; 
	}
}
