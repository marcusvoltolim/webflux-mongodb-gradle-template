package com.apirest.webflux.web.router.support;

import com.apirest.webflux.util.Constants;
import com.apirest.webflux.web.handler.support.UserHandler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.NonNull;

@Configuration
public class UserRouter extends CrudRouter {

	public UserRouter(@NonNull UserHandler userHandler) {
		super(Constants.API_V_1, "user", userHandler);
	}

	@Bean("UserRouterV1")
	@Override
	public RouterFunction<ServerResponse> routes() {
		return super.routes();
	}

}