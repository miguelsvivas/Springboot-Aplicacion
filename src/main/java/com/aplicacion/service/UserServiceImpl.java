package com.aplicacion.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacion.entity.User;
import com.aplicacion.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;
	
	@Override
	public Iterable<User> getAllUsers() {
		return repo.findAll();
	}
	
	private boolean checkUsernameAvaliable(User user)throws Exception {
		Optional<User> userFound = repo.findByUsername(user.getUsername());
		if(userFound.isPresent()){
			throw new Exception("Username no disponible");
		}
		return true;
	}
	
	
	private boolean checkPasswordValid(User user)throws Exception {
		if(user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			throw new Exception("confirm Password es obligatorio");
		}
		
		if( !user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y confirm password no coninciden ");
		}
		return true;
	}

	@Override
	public User createUser(User user)throws Exception {
		if(checkUsernameAvaliable(user) && checkPasswordValid(user)) {
			user = repo.save(user);
		}
		return user;
	}

	@Override
	public User getUserById(Long id) throws Exception {
		
		return repo.findById(id).orElseThrow(() -> new Exception ("El usuario para editar no existe"));
	}

	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return repo.save(toUser);
	
	}
	
	/**
	 * Map everythin but the password.
	 * @param from
	 * @param to
	 */
	protected void mapUser(User from,User to) {
		to.setUsername(from.getUsername());
		to.setFirstname(from.getFirstname());
		to.setLastname(from.getLastname());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		
		User user = getUserById(id);
		
		repo.delete(user);
		
	}

	
	
	
}
