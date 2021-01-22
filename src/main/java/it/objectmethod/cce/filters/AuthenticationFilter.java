package it.objectmethod.cce.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import it.objectmethod.cce.controllers.LoginController;
import it.objectmethod.cce.services.TokensService;

@Component
public class AuthenticationFilter implements javax.servlet.Filter {

	private static final Logger logger = LogManager.getLogger(LoginController.class);
	
	@Autowired
	TokensService tokenServ;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		
		String token = req.getHeader("token");
		String url = req.getRequestURI();
		boolean allow = false;

		logger.info("authenticationfilter, dofilter, uri: "+url);
		
		if (!url.equals("/login")) {

			logger.info("authenticationfilter, dofilter, token: "+token);
			
			boolean tokenValidity = tokenServ.tokenValidator(token);
			
			if (tokenValidity) {
				allow = true;
				logger.info("authenticationfilter, dofilter, allow: "+allow);
			} 
			
		} else {
			allow = true;
			logger.info("authenticationfilter, dofilter, allow: "+allow);
		}
		
		if (allow) {
			chain.doFilter(request, response);
		} else {
			resp.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}

}
