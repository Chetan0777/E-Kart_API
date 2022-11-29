package com.ekart.service;

import com.ekart.exception.LoginException;
import com.ekart.model.Login;

public interface CustomerLogin {
	
	public String logIntoAccount(Login dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
