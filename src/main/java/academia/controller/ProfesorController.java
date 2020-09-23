package academia.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academia.modelo.dao.impl.CursoDAOImpl;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

/**
 * Servlet implementation class ProfesorController
 */
@WebServlet("/privado/profesor")
public class ProfesorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// recuperar usuario de session
		HttpSession session = request.getSession();
		Usuario profesor = (Usuario)session.getAttribute("usuario_sesion");
		int idProfesor = profesor.getId();		
		
		// llamar al dao para recuperar cursos
		CursoDAOImpl dao = CursoDAOImpl.getInstance();			
		ArrayList<Curso> cursos = dao.listarByProfesor(idProfesor);
		
		// ir a una vista y enviar atributos para pintar
		request.setAttribute("cursos", cursos);
		request.getRequestDispatcher("profesor.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
