package academia.modelo.pojo;

public class Curso {
	
	private int id;
	private String nombre;
	private String identificador;
	private int horas;
	private Usuario profesor;
	private int numAlumnos;

	
	public Curso() {
		super();
		this.id = 0;
		this.nombre = "";
		this.identificador = "";
		this.horas = 0;
		this.profesor = new Usuario();
		this.numAlumnos = 0;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getIdentificador() {
		return identificador;
	}



	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}



	public int getHoras() {
		return horas;
	}



	public void setHoras(int horas) {
		this.horas = horas;
	}



	public Usuario getProfesor() {
		return profesor;
	}



	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}



	public int getNumAlumnos() {
		return numAlumnos;
	}



	public void setNumAlumnos(int numAlumnos) {
		this.numAlumnos = numAlumnos;
	}



	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", identificador=" + identificador + ", horas=" + horas
				+ ", profesor=" + profesor + ", numAlumnos=" + numAlumnos + "]";
	}

	
	
}
