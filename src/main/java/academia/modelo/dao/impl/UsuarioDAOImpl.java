package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import academia.modelo.ConnectionManager;
import academia.modelo.dao.UsuarioDAO;
import academia.modelo.pojo.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	
	private final static Logger LOG = Logger.getLogger(UsuarioDAOImpl.class);
	private static UsuarioDAOImpl INSTANCE = null;
	
	
	private UsuarioDAOImpl() {
		super();		
	}
	
	public static synchronized UsuarioDAOImpl getInstance() {
		if ( INSTANCE == null ) {
			INSTANCE = new UsuarioDAOImpl();
		}
		return INSTANCE;
	}

	
	private final static String SQL_BUSCAR = "SELECT id, nombre, apellidos, rol FROM usuarios WHERE nombre = ? AND password = MD5(?) ;"; 
	
	@Override
	public Usuario buscar(String nombre, String password) {
		Usuario usuario = null;
		try( Connection con = ConnectionManager.getConnection() ;
				 PreparedStatement pst = con.prepareStatement(SQL_BUSCAR);
				 
				){
				
				pst.setString(1, nombre);
				pst.setString(2, password);
				
				LOG.debug(pst);
			
				try (ResultSet rs = pst.executeQuery() ) {	
			
					while ( rs.next() ) {				
						
						usuario = new Usuario();
						usuario.setId(rs.getInt("id"));
						usuario.setNombre(rs.getString("nombre"));
						usuario.setApellidos( rs.getString("apellidos"));
						usuario.setRol( rs.getInt("rol"));
					}
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		return usuario;
	}

}
