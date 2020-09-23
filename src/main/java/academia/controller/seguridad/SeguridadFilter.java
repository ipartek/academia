package academia.controller.seguridad;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import academia.modelo.pojo.Usuario;

/**
 * Servlet Filter implementation class SeguridadFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/privado/*" })
public class SeguridadFilter implements Filter {
	
	
	private final static Logger LOG = Logger.getLogger(SeguridadFilter.class);

    

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOG.trace("Filtro destruido");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		LOG.debug("Filtrando url: " + request.getServletPath() );
		
		
		HttpSession session = request.getSession();
		Usuario uSession = (Usuario) session.getAttribute("usuario_sesion");
		
		if ( uSession == null ){
			LOG.debug("Usuario sin sesion iniciada o caducada ");
			response.sendRedirect( request.getContextPath() + "/login.jsp");
			
		}else {
			LOG.debug("Usuario logeado en sesion, podemos continuar");
			// pass the request along the filter chain
			chain.doFilter(request, res);
		}	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.trace("Filtro iniciado");
	}

}
