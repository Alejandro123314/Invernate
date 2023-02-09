package principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Copia;
import entidades.Libro;

public class EliminarMayorCopia {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		em.createQuery("DELETE FROM Copia_Digital").executeUpdate();

//		List<Copia> copias = em.createQuery("DELETE c FROM Copia c WHERE Libro", Copia.class).getResultList();
		

	}
}
