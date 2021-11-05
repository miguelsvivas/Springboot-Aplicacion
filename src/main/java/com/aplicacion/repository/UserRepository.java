package com.aplicacion.repository;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;

import com.aplicacion.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

	public Optional<User> findByUsername(String username);
}
