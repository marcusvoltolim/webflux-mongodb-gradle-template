package com.apirest.webflux.service.support;

import com.apirest.webflux.document.support.User;
import com.apirest.webflux.repository.support.UserRepository;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService extends CrudImplService<UserRepository, User> {

	public UserService(UserRepository repository) {
		super(repository, User.class);
	}

}