package com.lambdaschool.deployshoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * The type Authorization server config.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter
{
	/**
	 * The Client id.
	 */
	static final String CLIENT_ID = System.getenv("OAUTHCLIENTID");
	
	/**
	 * The Client secret.
	 */
	static final String CLIENT_SECRET = System.getenv("OAUTHCLIENTSECRET");
	
	/**
	 * The Grant type password.
	 */
	static final String GRANT_TYPE_PASSWORD = "password";
	
	/**
	 * The Authorization code.
	 */
	static final String AUTHORIZATION_CODE = "authorization_code";
	
	/**
	 * The Scope read.
	 */
	static final String SCOPE_READ = "read";
	
	/**
	 * The Scope write.
	 */
	static final String SCOPE_WRITE = "write";
	
	/**
	 * The Trust.
	 */
	static final String TRUST = "trust";
	
	/**
	 * The Access token validity seconds.
	 */
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = -1;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override public void configure(ClientDetailsServiceConfigurer clients) throws Exception
	{
		clients.inMemory()
				.withClient(CLIENT_ID)
				.secret(encoder.encode(CLIENT_SECRET))
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE)
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
	}
	
	@Override public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
	{
		endpoints.tokenStore(tokenStore)
				.authenticationManager(authenticationManager);
		endpoints.pathMapping("/oauth/token", "/login");
	}
}
