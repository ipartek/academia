package academia.modelo.dao;

import java.util.ArrayList;

import academia.modelo.pojo.Curso;

public interface CursoDAO {
	
	/**
	 * Listado de cursos sin datos de Alumnos matriculados
	 * @return ArrayList&lt;Curso&gt;
	 */
	ArrayList<Curso> listar();

	
	/**
	 * Listado de cursos sin datos de Alumnos matriculados para un <code>(Usuario)</code>profesor 
	 * @param idProfesor int identificador del profesor
	 * @return ArrayList&lt;Curso&gt;
	 */
	ArrayList<Curso> listarByProfesor( int idProfesor );
	
	
}
