package lu.findl.multitenant.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import lu.findl.multitenant.helpers.TokenHelper;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private final Log logger = LogFactory.getLog(this.getClass());

	private TokenHelper tokenHelper;

	private AppUserDetailsService userDetailsService;

	public TokenAuthenticationFilter(TokenHelper tokenHelper, AppUserDetailsService userDetailsService) {
		this.tokenHelper = tokenHelper;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("In TokenAuthenticationFilter");

		String username = null;
		String authToken = tokenHelper.getToken(request);
		if (authToken != null) {
			System.out.println("authToken != null");
			// get username from token
			try {
				username = tokenHelper.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				logger.error("an error occured during getting username from token", e);
			}

			if (username != null) {
				System.out.println("username != null");
				// get user
				AppUserDetails userDetails = (AppUserDetails) userDetailsService.loadUserByUsername(username);
				// create authentication
				TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
				authentication.setToken(authToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);
	}
}
