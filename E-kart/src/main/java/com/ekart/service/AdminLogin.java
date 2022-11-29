package com.ekart.service;

import com.ekart.exception.LoginException;
import com.ekart.model.CurrentAdminSession;
import com.ekart.model.Login;

public interface AdminLogin {
	
	public CurrentAdminSession adminLog(Login dto)throws LoginException;

	public String adminLogOut(String key)throws LoginException;

}
