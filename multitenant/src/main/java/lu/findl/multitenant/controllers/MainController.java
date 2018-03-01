package lu.findl.multitenant.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lu.findl.multitenant.entities.Account;
import lu.findl.multitenant.helpers.Reponse;
import lu.findl.multitenant.security.AppUserDetails;
import lu.findl.multitenant.services.IMetier;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

	@Autowired
	private IMetier metier;

	@RequestMapping(value = "/whoami", method = RequestMethod.GET)
	public ResponseEntity<?> whoami() throws InterruptedException {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Account account = ((AppUserDetails) auth.getPrincipal()).getAccount();

			Map<String, Object> res = new HashMap<>();
			res.put("idAccount", account.getId());
			res.put("username", account.getUsername());
			res.put("roleName", account.getRoleName());

			return ResponseEntity.status(HttpStatus.OK).body(new Reponse(0, res));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
	}

}
