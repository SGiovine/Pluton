package it.objectmethod.cce.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter implements javax.servlet.Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String token = req.getHeader("token");
		String url = req.getRequestURI();
		boolean allow = false;
		
		System.out.println(url);
		System.out.println(token);

		if (!url.equals("/login")) {
			System.out.println("diverso da login");
			if ("1".equals(token)) {
				System.out.println("token = 1");
				allow = true;
			} 
			
		} else {
			System.out.println("percorso login");
			allow = true;
		}
		
		System.out.println(allow);
		if (allow) {
			chain.doFilter(request, response);
		} else {
			resp.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
		System.out.println("--------------------------------------");
	}

}
