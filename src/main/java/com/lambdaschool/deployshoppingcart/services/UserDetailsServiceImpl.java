package com.lambdaschool.deployshoppingcart.services;

import com.lambdaschool.deployshoppingcart.exceptions.ResourceNotFoundException;
import com.lambdaschool.deployshoppingcart.models.User;
import com.lambdaschool.deployshoppingcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type User details service.
 */
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserRepository userrepos;
	
	@Transactional
	@Override public UserDetails loadUserByUsername(String s) throws ResourceNotFoundException
	{
		User user = userrepos.findByUsername(s.toLowerCase());
		
		if (user == null)
		{
			throw new ResourceNotFoundException("Invalid username or password");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getAuthority());
	}
}
