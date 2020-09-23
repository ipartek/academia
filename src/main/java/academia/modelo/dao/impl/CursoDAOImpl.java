package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import academia.modelo.ConnectionManager;
import academia.modelo.dao.CursoDAO;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

public class CursoDAOImpl implements CursoDAO {

	private final static Logger LOG = Logger.getLogger(CursoDAOImpl.class);
	private static CursoDAOImpl INSTANCE = null;
	
	
	private CursoDAOImpl() {
		super();		
	}
	
	public static synchronized CursoDAOImpl getInstance() {
		if ( INSTANCE == null ) {
			INSTANCE = new CursoDAOImpl();
		}
		return INSTANCE;
	}


	private final static String CONSULTA = " SELECT "
			+ "	c.id as 'curso_id',  "
			+ "	c.identificador,  "
			+ " c.nombre as 'curso_nombre',  "
			+ " c.horas,  "
			+ " f.id as 'profesor_id',  "
			+ " f.nombre as 'profesor_nombre',  "
			+ " f.apellidos as 'profesor_apellidos',  "
			+ " rol, "
			+ " ( SELECT COUNT(id_alumno) FROM alumnos_curso WHERE id_curso = c.id GROUP BY (id_curso) ) as 'num_alumnos' "
			+ " FROM cursos c, usuarios f  "
			+ " WHERE  "
			+ "	c.id_profesor = f.id ";

	private final static String ORDER_LIMIT = " ORDER BY curso_nombre ASC LIMIT 500 ; ";

	private final static String SQL_LISTAR = CONSULTA + ORDER_LIMIT;

	private final static String SQL_LISTAR_BY_PROFESOR = CONSULTA + " AND c.id_profesor = ? " + ORDER_LIMIT;

	@Override
	public ArrayList<Curso> listar() {

		ArrayList<Curso> cursos = new ArrayList<Curso>();
		LOG.debug(SQL_LISTAR);

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_LISTAR);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {

				Curso c = mapper(rs);
				cursos.add(c);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cursos;
	}

	@Override
	public ArrayList<Curso> listarByProfesor(int idProfesor) {

		ArrayList<Curso> cursos = new ArrayList<Curso>();
		

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_LISTAR_BY_PROFESOR);

		) {

			pst.setInt(1, idProfesor);
			LOG.debug(SQL_LISTAR_BY_PROFESOR);

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {

					cursos.add(mapper(rs));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cursos;
	}

	private Curso mapper(ResultSet rs) throws SQLException {

		Curso c = new Curso();
		c.setId(rs.getInt("curso_id"));
		c.setNombre(rs.getString("curso_nombre"));
		c.setIdentificador(rs.getString("identificador"));
		c.setHoras(rs.getInt("horas"));
		c.setNumAlumnos(rs.getInt("num_alumnos"));

		Usuario p = new Usuario();
		p.setId(rs.getInt("profesor_id"));
		p.setNombre(rs.getString("profesor_nombre"));
		p.setApellidos(rs.getString("profesor_apellidos"));
		p.setRol(rs.getInt("rol"));

		c.setProfesor(p);

		return c;
	}

}
