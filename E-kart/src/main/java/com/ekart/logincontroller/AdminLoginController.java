package com.ekart.logincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.exception.LoginException;
import com.ekart.model.CurrentAdminSession;
import com.ekart.model.Login;
import com.ekart.service.AdminLogin;

@RestController
@RequestMapping("/adminlogin")
public class AdminLoginController {
	
	@Autowired
	private AdminLogin al;
	
	@PostMapping("/login")
	public ResponseEntity<CurrentAdminSession> adminLogin(@RequestBody Login dto) throws LoginException{
		CurrentAdminSession res=al.adminLog(dto);
		return new ResponseEntity<CurrentAdminSession>(res,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> adminLogout(@RequestParam(required = false)String key) throws LoginException{
		String res=al.adminLogOut(key);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
	}

}
