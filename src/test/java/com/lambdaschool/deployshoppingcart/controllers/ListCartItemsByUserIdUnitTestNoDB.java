package com.lambdaschool.deployshoppingcart.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.deployshoppingcart.DeployshoppingcartApplication;
import com.lambdaschool.deployshoppingcart.DeployshoppingcartApplicationUnitTestNoDB;
import com.lambdaschool.deployshoppingcart.models.CartItem;
import com.lambdaschool.deployshoppingcart.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DeployshoppingcartApplication.class)
@WebAppConfiguration
public class ListCartItemsByUserIdUnitTestNoDB
{
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	private User userFromJson(String json, Class<User> userModelClass) throws JsonProcessingException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, userModelClass);
	}
	
	private CartItem cartItemFromJson(String json, Class<CartItem> cartItemClass) throws JsonProcessingException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, cartItemClass);
	}
	
	@Before
	public void setUp() throws Exception
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void tearDown() throws Exception
	{
	}
	
	@Test
	public void listCartItemsByUserId() throws Exception
	{
		String uri = "/carts/user/1";
		MvcResult result =
				mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = result.getResponse().getContentAsString();
		User user = userFromJson(content, User.class);
		assertNotNull(user);
		assertEquals("barnbarn", user.getUsername());
	}
}