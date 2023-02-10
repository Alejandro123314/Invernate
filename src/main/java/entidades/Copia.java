package entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Copia {
	
	@Id
	@GeneratedValue
	private int id_copia;
	private String editorial;
	private int anio_publicacion;

	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_DNI"))
	private Persona prestatario;

	public Copia() {

	}

	public Copia(String editorial, int anio_publicacion) {
		this.editorial = editorial;
		this.anio_publicacion = anio_publicacion;
	}

	public int getAnio_publicacion() {
		return anio_publicacion;
	}

	public void setAnio_publicacion(int anio_publicacion) {
		this.anio_publicacion = anio_publicacion;
	}

	public int getId_copia() {
		return id_copia;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	// Getters y Setters de Relaciones
	public Persona getPrestatario() {
		return prestatario;
	}

	public void addPrestatario(Persona pres) {
		if (this.prestatario != null)
			throw new RuntimeException("Esta copia ya está prestada");
		this.prestatario = pres;
		pres.getLibros().add(this);
	}

	public void removePrestatario() {
		if (this.prestatario == null)
			throw new RuntimeException("Esta copia no está prestada");
		this.prestatario.getLibros().remove(this);
		prestatario = null;
	}

	public Copia_Digital anidadirCopiaDigital(String fich, String form, int tamanio) {
		Copia_Digital nuevaCopia = new Copia_Digital(fich, form, tamanio);
		nuevaCopia.setOriginal(this);
		return nuevaCopia;
	}

	@Override
	public String toString() {
		String s = String.format("Copia [%d]", id_copia);
		s = s + (prestatario != null?
				" / prestado a "+prestatario.getApellidos()+" "+prestatario.getNombre() +
				" [" + prestatario.getDni() + "]"
				: "");
		return s;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Copia other = (Copia) obj;
		if (id_copia != other.getId_copia())
			return false;
		return true;
	}
}