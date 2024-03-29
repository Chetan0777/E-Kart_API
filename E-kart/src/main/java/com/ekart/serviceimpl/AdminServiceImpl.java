package com.ekart.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekart.exception.AdminException;
import com.ekart.model.Admin;
import com.ekart.model.CurrentAdminSession;
import com.ekart.repository.AdminRepo;
import com.ekart.repository.AdminSessionRepo;
import com.ekart.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepo ar;
	
	@Autowired
	private AdminSessionRepo asRepo;

	@Override
	public Admin registerAdmin(Admin admin, String validationKey) throws AdminException {
		if(!validationKey.equals("chetan123"))
			throw new AdminException("you don't have authority to register as admin");
		
		return ar.save(admin);
	}

	@Override
	public List<Admin> viewAllAdmin(String key) throws AdminException {
		List<CurrentAdminSession>check=asRepo.findByUuid(key);
		
		if(check.size()==0)
			throw new AdminException("you don't have authority to see admin list");
		
		return ar.findAll();

	}

	@Override
	public Admin deleteAdmin(Admin admin, String key) throws AdminException {
		List<CurrentAdminSession>check=asRepo.findByUuid(key);
		
		if(check.size()==0)
			throw new AdminException("you don't have authority to delete admin");
		
		Optional<Admin>opt=ar.findById(admin.getAdminId());
		
		if(opt.isEmpty())
			throw new AdminException("admin not found with id "+admin.getAdminId());
		
		ar.delete(opt.get());
		
		return opt.get();
				
	}

}
