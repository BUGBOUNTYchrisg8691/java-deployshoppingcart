package com.lambdaschool.deployshoppingcart.models;

import javax.validation.constraints.Email;

/**
 * The type User minimum.
 */
public class UserMinimum
{
	private String username;
	private String password;
	
	@Email
	private String primaryemail;
	
	/**
	 * Gets username.
	 *
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}
	
	/**
	 * Sets username.
	 *
	 * @param username the username
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	/**
	 * Gets primaryemail.
	 *
	 * @return the primaryemail
	 */
	public String getPrimaryemail()
	{
		return primaryemail;
	}
	
	/**
	 * Sets primaryemail.
	 *
	 * @param primaryemail the primaryemail
	 */
	public void setPrimaryemail(String primaryemail)
	{
		this.primaryemail = primaryemail;
	}
}
