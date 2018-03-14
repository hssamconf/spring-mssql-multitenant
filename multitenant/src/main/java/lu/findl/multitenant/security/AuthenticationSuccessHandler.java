package lu.findl.multitenant.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lu.findl.multitenant.entities.central.Account;
import lu.findl.multitenant.helpers.TokenHelper;
import lu.findl.multitenant.services.ICentralService;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Value("${jwt.expires_in}")
	private int EXPIRES_IN;

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Autowired
	TokenHelper tokenHelper;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ICentralService metier;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// System.out.println("onAuthenticationSuccess");

		clearAuthenticationAttributes(request);
		Account account = ((AppUserDetails) authentication.getPrincipal()).getAccount();
		String jws = tokenHelper.generateToken(account.getUsername());
		// Create token auth Cookie
		Cookie authCookie = new Cookie(TOKEN_COOKIE, (jws));
		authCookie.setPath("/");
		authCookie.setHttpOnly(true);
		authCookie.setSecure(false);
		authCookie.setMaxAge(EXPIRES_IN);
		// Add cookie to response
		response.addCookie(authCookie);
		// JWT is also in the response
		AccountTokenState userTokenState = new AccountTokenState(jws, EXPIRES_IN, account.getRoleName());
		String jwtResponse = objectMapper.writeValueAsString(userTokenState);
		response.setContentType("application/json");
		response.getWriter().write(jwtResponse);
	}

}
