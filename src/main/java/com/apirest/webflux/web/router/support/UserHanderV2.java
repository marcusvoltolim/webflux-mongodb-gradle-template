package com.apirest.webflux.web.router.support;

import com.apirest.webflux.web.handler.support.UserHandler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.NonNull;

@Configuration
public class UserHanderV2 extends CrudRouter {

	public UserHanderV2(@NonNull UserHandler userHandler) {
		super("/api/v2", "user", userHandler);
	}

	@Bean("UserRouterV2")
	@Override
	public RouterFunction<ServerResponse> routes() {
		return super.routes();
	}

}