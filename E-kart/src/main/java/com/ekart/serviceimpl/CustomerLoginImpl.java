package com.ekart.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekart.exception.LoginException;
import com.ekart.model.CurrentUserSession;
import com.ekart.model.Customer;
import com.ekart.model.Login;
import com.ekart.repository.CustomerRepo;
import com.ekart.repository.UserSessionRepo;
import com.ekart.service.CustomerLogin;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerLoginImpl implements CustomerLogin{

	@Autowired
	private UserSessionRepo usr;
	
	@Autowired
	private CustomerRepo crl;
	
	@Override
	public String logIntoAccount(Login dto) throws LoginException {
		List<Customer> list=crl.findByMobile(dto.getMobile());
		
		if(list.size()==0) {
			throw new LoginException("please enter valid mobile number");
		}
		
		Customer customer=list.get(0);
		Optional<CurrentUserSession> validation=usr.findById(customer.getCustomerId());
		
		if(validation.isPresent()) {
			throw new LoginException("user already logged in either this number");
		}
		
		if(customer.getPassword().equals(dto.getPassword())) {
			String key=RandomString.make(6);
			CurrentUserSession cus=new CurrentUserSession();
			cus.setUserId(customer.getCustomerId());
			cus.setUuid(key);
			cus.setLocalDateTime(LocalDateTime.now());
			usr.save(cus);
			return cus.toString();
		}
		
		throw new LoginException("please enter valid password");
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		List<CurrentUserSession> validation=usr.findByUuid(key);
		if(validation.size()==0) {
			throw new LoginException("user not logged in with this number");
		}
		usr.delete(validation.get(0));
		return "Logged out!....";
	}

}
