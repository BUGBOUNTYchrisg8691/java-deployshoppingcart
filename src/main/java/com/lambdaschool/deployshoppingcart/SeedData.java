package com.lambdaschool.deployshoppingcart;

import com.lambdaschool.deployshoppingcart.models.*;
import com.lambdaschool.deployshoppingcart.repository.CartItemRepository;
import com.lambdaschool.deployshoppingcart.repository.ProductRepository;
import com.lambdaschool.deployshoppingcart.repository.RoleRepository;
import com.lambdaschool.deployshoppingcart.repository.UserRepository;
import com.lambdaschool.deployshoppingcart.services.RoleService;
import com.lambdaschool.deployshoppingcart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ConditionalOnProperty(prefix = "command.line.runner", value = "enabled", havingValue = "true", matchIfMissing = true)
//@Component
public class SeedData implements CommandLineRunner
{
	@Autowired
	private RoleRepository rolerepos;
	
	@Autowired
	private UserRepository userrepos;
	
	@Autowired
	private CartItemRepository cartrepos;
	
	@Autowired
	private ProductRepository prodrepos;
	
	@Transactional
	@Override public void run(String... args) throws Exception
	{
		userrepos.deleteAll();
		rolerepos.deleteAll();
		
		Role r1 = new Role("admin");
		Role r2 = new Role("user");
		
		r1 = rolerepos.save(r1);
		r2 = rolerepos.save(r2);
		
		User u1 = new User("barnbarn",
				"LambdaLlama",
				"barnbarn@host.local",
				"");
		
		u1.getRoles().add(new UserRoles(u1, r1));
		u1.getRoles().add(new UserRoles(u1, r2));
		
		User u2 = new User("cinnamon",
				"LambdaLlama",
				"cinnamon@host.local",
				"");
		
		u2.getRoles().add(new UserRoles(u2, r2));
		
		User u3 = new User("stumps",
				"LambdaLlama",
				"stumps@host.local",
				"");
		
		u3.getRoles().add(new UserRoles(u3, r2));
		
		Product p1 = new Product();
		p1.setName("pen");
		p1.setDescription("makes words");
		p1.setPrice(2.50);
		p1.setComments("");
		
		Product p2 = new Product();
		p2.setName("pencil");
		p2.setDescription("does math");
		p2.setPrice(1.50);
		p2.setComments("");
		
		Product p3 = new Product();
		p3.setName("coffee");
		p3.setDescription("everyone needs coffee");
		p3.setPrice(4.00);
		p3.setComments("");
		
		CartItem ci1 = new CartItem(u1, p1, 4, "");
		CartItem ci2 = new CartItem(u1, p2, 3, "");
		CartItem ci3 = new CartItem(u1, p3, 2, "");
		CartItem ci4 = new CartItem(u2, p3, 1, "");
		CartItem ci5 = new CartItem(u3, p3, 17, "");
		
		prodrepos.save(p1);
		prodrepos.save(p2);
		prodrepos.save(p3);
		
		cartrepos.save(ci1);
		cartrepos.save(ci2);
		cartrepos.save(ci3);
		cartrepos.save(ci4);
		cartrepos.save(ci5);
		
		userrepos.save(u1);
		userrepos.save(u2);
		userrepos.save(u3);
	}
}
