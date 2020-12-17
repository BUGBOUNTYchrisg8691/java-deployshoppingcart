package com.lambdaschool.deployshoppingcart.controllers;

import com.lambdaschool.deployshoppingcart.models.User;
import com.lambdaschool.deployshoppingcart.models.UserMinimum;
import com.lambdaschool.deployshoppingcart.models.UserRoles;
import com.lambdaschool.deployshoppingcart.services.RoleService;
import com.lambdaschool.deployshoppingcart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * The type Register controller.
 */
@RestController
public class RegisterController
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * Register self response entity.
	 *
	 * @param httpServletRequest the http servlet request
	 * @param user               the user
	 * @return the response entity
	 * @throws URISyntaxException the uri syntax exception
	 */
	@PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> registerSelf(HttpServletRequest httpServletRequest,
	                                      @Valid @RequestBody UserMinimum user) throws URISyntaxException
	{
		User newUser = new User();
		
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		newUser.setPrimaryemail(user.getPrimaryemail());
		
		Set<UserRoles> newRoles = new HashSet<>();
		newRoles.add(new UserRoles(newUser, roleService.findByName("user")));
		newUser.setRoles(newRoles);
		
		newUser = userService.save(newUser);
		
		HttpHeaders respHeaders = new HttpHeaders();
		URI newUserUri =
				ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/user/{userId}").buildAndExpand(newUser.getUserid()).toUri();
		respHeaders.setLocation(newUserUri);
		
		RestTemplate restTemplate = new RestTemplate();
		String requestUri = "http://localhost" + ":" + httpServletRequest.getLocalPort() + "/login";
		List<MediaType> acceptableMediaTypes = new ArrayList<>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(acceptableMediaTypes);
		headers.setBasicAuth(System.getenv("OAUTHCLIENTID"), System.getenv("OAUTHCLIENTSECRET"));
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "password");
		map.add("scope", "read write trust");
		map.add("username", user.getUsername());
		map.add("password", user.getPassword());
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		
		String theToken = restTemplate.postForObject(requestUri, request, String.class);
		
		return new ResponseEntity<>(theToken, respHeaders, HttpStatus.CREATED);
	}
}
