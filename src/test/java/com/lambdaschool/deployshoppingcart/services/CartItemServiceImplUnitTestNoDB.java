package com.lambdaschool.deployshoppingcart.services;

import com.lambdaschool.deployshoppingcart.DeployshoppingcartApplicationUnitTestNoDB;
import com.lambdaschool.deployshoppingcart.models.*;
import com.lambdaschool.deployshoppingcart.repository.CartItemRepository;
import com.lambdaschool.deployshoppingcart.repository.ProductRepository;
import com.lambdaschool.deployshoppingcart.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeployshoppingcartApplicationUnitTestNoDB.class,
                properties = {"command.line.runner.enabled=false"})
public class CartItemServiceImplUnitTestNoDB
{
	@Autowired
	private CartItemService cartitemService;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private ProductRepository productRepository;
	
	@MockBean
	private CartItemRepository cartItemRepository;
	
	private List<CartItem> cartItemList = new ArrayList<>();
	private List<User> userList = new ArrayList<>();
	private List<Product> productList = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception
	{
		Role r1 = new Role("admin");
		Role r2 = new Role("user");
		
		r1.setRoleid(1);
		r2.setRoleid(2);
		
		User u1 = new User("barnbarn",
				"LambdaLlama",
				"barnbarn@host.local",
				"");
		u1.getRoles().add(new UserRoles(u1, r1));
		u1.getRoles().add(new UserRoles(u1, r2));
		u1.setUserid(10);
		
		User u2 = new User("cinnamon",
				"LambdaLlama",
				"cinnamon@host.local",
				"");
		u2.getRoles().add(new UserRoles(u2, r2));
		u2.setUserid(11);
		
		User u3 = new User("stumps",
				"LambdaLlama",
				"stumps@host.local",
				"");
		u3.getRoles().add(new UserRoles(u3, r2));
		u3.setUserid(12);
		
		Product p1 = new Product();
		p1.setName("pen");
		p1.setDescription("makes words");
		p1.setPrice(2.50);
		p1.setComments("");
		p1.setProductid(15);
		
		Product p2 = new Product();
		p2.setName("pencil");
		p2.setDescription("does math");
		p2.setPrice(1.50);
		p2.setComments("");
		p2.setProductid(16);
		
		Product p3 = new Product();
		p3.setName("coffee");
		p3.setDescription("everyone needs coffee");
		p3.setPrice(4.00);
		p3.setComments("");
		p3.setProductid(17);
		
		CartItem ci1 = new CartItem(u1, p1, 4, "");
		CartItem ci2 = new CartItem(u1, p2, 3, "");
		CartItem ci3 = new CartItem(u1, p3, 2, "");
		CartItem ci4 = new CartItem(u2, p3, 1, "");
		CartItem ci5 = new CartItem(u3, p3, 17, "");
		
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		
		productList.add(p1);
		productList.add(p2);
		productList.add(p3);
		
		cartItemList.add(ci1);
		cartItemList.add(ci2);
		cartItemList.add(ci3);
		cartItemList.add(ci4);
		cartItemList.add(ci5);
	}
	
	@After
	public void tearDown() throws Exception
	{
	}
	
	@Test
	public void addToCart()
	{
		CartItem cartItem = new CartItem();
		cartItem.setProduct(productList.get(1));
		cartItem.setComments("");
		cartItem.setQuantity(2L);
		cartItem.setUser(userList.get(1));
		
		Product product = productList.get(1);
		User user = userList.get(1);
		
		CartItemId cartItemId = new CartItemId(user.getUserid(), product.getProductid());
		
		Mockito.when(userRepository.findById(user.getUserid())).thenReturn(Optional.of(user));
		Mockito.when(productRepository.findById(product.getProductid())).thenReturn(Optional.of(product));
		Mockito.when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(cartItem));
		Mockito.when(cartItemRepository.save(any(CartItem.class))).thenReturn(cartItem);
		
		CartItem addedCartItem = cartitemService.addToCart(user.getUserid(), product.getProductid(), "");
		assertNotNull(addedCartItem);
		assertEquals(productList.get(1).getName(), addedCartItem.getProduct().getName());
		assertEquals(3L, addedCartItem.getQuantity());
		assertEquals(user.getUsername(), addedCartItem.getUser().getUsername());
	}
	
	@Test
	public void removeFromCart()
	{
		CartItem cartItem = new CartItem();
		cartItem.setProduct(productList.get(1));
		cartItem.setComments("");
		cartItem.setQuantity(2L);
		cartItem.setUser(userList.get(1));
		
		Product product = productList.get(1);
		User user = userList.get(1);
		
		CartItemId cartItemId = new CartItemId(user.getUserid(), product.getProductid());
		
		Mockito.when(userRepository.findById(user.getUserid())).thenReturn(Optional.of(user));
		Mockito.when(productRepository.findById(product.getProductid())).thenReturn(Optional.of(product));
		Mockito.when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(cartItem));
		Mockito.when(cartItemRepository.save(any(CartItem.class))).thenReturn(cartItem);
		
		CartItem removedCartItem = cartitemService.removeFromCart(user.getUserid(), product.getProductid(), "");
		assertNotNull(removedCartItem);
		assertEquals(1L, removedCartItem.getQuantity());
	}
}