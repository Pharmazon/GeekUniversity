package com.flamexander.spring.jwt.dao;

import com.flamexander.spring.jwt.model.DaoUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<DaoUser, Long> {
	DaoUser findByUsername(String username);
}