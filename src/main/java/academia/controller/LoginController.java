package academia.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academia.modelo.dao.impl.UsuarioDAOImpl;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		UsuarioDAOImpl dao = new UsuarioDAOImpl();
		
		Usuario usuario = dao.buscar(nombre, password);
		

		if ( usuario == null ) {
			request.setAttribute("mensaje", "Credenciales incorrectas, prueba de nuevo por favor");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}else if ( usuario.getRol() == Usuario.ROL_PROFESOR ) {
			
			int idProfersor = usuario.getId();			
			ArrayList<Curso> cursos = new ArrayList<Curso>();
			// Crea el DAO de Cursos y obtento todos los cursos de ese profesor por su id
			request.setAttribute("cursos", cursos);
			request.getSession().setAttribute("usuario_sesion", usuario);
			request.getRequestDispatcher("privado/profesor.jsp").forward(request, response);
			
			
		}else {
			
			request.getSession().setAttribute("usuario_sesion", usuario);
			request.getRequestDispatcher("privado/alumno").forward(request, response);
		}
			
			
		
		
	}

}
