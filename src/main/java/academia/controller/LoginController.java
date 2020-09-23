package academia.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academia.modelo.dao.impl.UsuarioDAOImpl;
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
			
			request.getSession().setAttribute("usuario_sesion", usuario);
			
			// peticion interna dentro del servidor
			// request.getRequestDispatcher("privado/profesor").forward(request, response);
			
			// obligamos hacer una nueva peticion al cliente (browser)
			response.sendRedirect( request.getContextPath() + "/privado/profesor");
			
			
			
		}else {
			
			request.getSession().setAttribute("usuario_sesion", usuario);
			request.getRequestDispatcher("privado/alumno").forward(request, response);
		}
			
			
		
		
	}

}
