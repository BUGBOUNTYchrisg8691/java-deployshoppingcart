package com.lambdaschool.deployshoppingcart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;


/**
 * The type Resource server config.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
	/**
	 * The constant RESOURCE_ID.
	 */
	public static final String RESOURCE_ID = "resource_id";
	
	@Override public void configure(ResourceServerSecurityConfigurer resources) throws Exception
	{
		resources.resourceId(RESOURCE_ID).stateless(false);
	}
	
	@Override public void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
				.antMatchers("/",
						"/h2-console/**",
						"/swagger-resources/**",
						"/swagger-resource/**",
						"/swagger-ui.html",
						"/v2/api-docs",
						"/webjars/**",
						"/register")
				.permitAll()
				.antMatchers("/roles/**", "/products/**")
				.hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/users/user")
				.hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/users/user/{id}")
				.hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/users/user/{id}")
				.hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/users/user/name/{username}")
				.hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/users/user/name/like/{username}")
				.hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/users/user")
				.hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.PATCH, "/users/user/{id}")
				.hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/users/user/{id}")
				.hasAnyRole("ADMIN")
				.antMatchers("/users/**",
						"/oauth/revoke-token",
						"/logout",
						"/carts/**")
				.authenticated()
				.antMatchers("/roles/**")
				.hasAnyRole("ADMIN")
				.and()
				.exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());
				
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.logout().disable();
	}
}
