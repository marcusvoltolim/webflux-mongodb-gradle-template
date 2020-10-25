package com.apirest.webflux.web.handler.support;

import com.apirest.webflux.document.support.User;
import com.apirest.webflux.service.support.UserService;

import org.springframework.stereotype.Component;

import lombok.NonNull;

@Component
public class UserHandler extends CrudHandler<UserService, User> {

	public UserHandler(@NonNull UserService service) {
		super(service);
	}



}