package com.ekart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ekart.model.CurrentUserSession;

@Repository
public interface UserSessionRepo extends JpaRepository<CurrentUserSession, Integer>{
	
	public List<CurrentUserSession> findByUuid(String uuid);
	
}
