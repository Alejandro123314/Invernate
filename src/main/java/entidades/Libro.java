package entidades;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Libro {

	@Id
	private String isbn;
	private String titulo;
	private int anio_escritura;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	// Unidireccional. No tiene atributo sobre el que declarar "mappedBy"
	@JoinColumn(name = "isbn_libro",foreignKey = @ForeignKey(name = "ISBN_LIBRO_FK")) // nombre fkey, en la tabla copia para referenciar al libro
	private List<Copia> copias = new ArrayList<Copia>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })// , mappedBy = "libros")
	private List<Autor> autores = new ArrayList<Autor>();

	// Constructores
	public Libro() {
	}

	public Libro(String ISBN, String Titulo, int Año_escritura, List<Autor> autores) {
		this.isbn = ISBN;
		this.titulo = Titulo;
		this.anio_escritura = Año_escritura;
		for (Autor autor : autores) {
			addAutor(autor);
		}
		copias.add(new Copia());
	}

	public Libro(String ISBN, String Titulo, int Año_escritura, Autor autor) {
		this(ISBN,Titulo, Año_escritura, Arrays.asList(autor));
	}

	public Libro(String ISBN, String Titulo, int Año_escritura) {
		this(ISBN, Titulo, Año_escritura, new ArrayList<Autor>());
	}

	// Getters y Setters
	public String getISBN() {
		return isbn;
	}

	public void setISBN(String iSBN) {
		isbn = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAño_escritura() {
		return anio_escritura;
	}

	public void setAño_escritura(int año_escritura) {
		anio_escritura = año_escritura;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void addAutor(Autor autor) {
		autores.add(autor);
		autor.getObras().add(this);
	}

	public void removeAutor(Autor autor) {
		autores.remove(autor);
		autor.getObras().remove(this);
	}

	public List<Copia> getCopias() {
		return copias;
	}

	public void addCopia(String editorial, int anio_publicacion) {
		copias.add(new Copia(editorial, anio_publicacion));
	}

	public void removeCopia(Copia c) {
		copias.remove(c);
	}

	// toString y equals
	@Override
	public String toString() {
		String sAutores = "";
		if (autores.size() == 0)
			sAutores = "Autor anónimo";
		else {
			if (autores.size() == 1)
				sAutores = "Autor ";
			else
				sAutores = "Autores ";
			Iterator<Autor> it = autores.iterator();
			for (int i = 0; i < autores.size() - 1; i++) {
				Autor aux = it.next();
				sAutores += aux.getNombre() + ", ";
			}
			sAutores += it.next().getNombre();
		}
		return String.format("\"%s\" [ed. %s ISBN %s public. %s] (%s copias)", titulo, isbn,
				anio_escritura, sAutores, copias.size());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (isbn != other.getISBN())
			return false;
		return true;
	}
}