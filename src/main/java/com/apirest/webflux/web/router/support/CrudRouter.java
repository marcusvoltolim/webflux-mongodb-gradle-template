package com.apirest.webflux.web.router.support;

import com.apirest.webflux.web.handler.support.CrudHandler;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.NonNull;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public abstract class CrudRouter {

	private final String apiPath;

	private final CrudHandler<?, ?> handler;

	public CrudRouter(@NonNull String apiVersion, @NonNull String api, @NonNull CrudHandler<?, ?> handler) {
		if (apiVersion.isBlank() || api.isBlank()) {
			throw new IllegalArgumentException("Path não informado!");
		}
		this.apiPath = apiVersion + "/" + api;
		this.handler = handler;
	}

	public RouterFunction<ServerResponse> routes() {
		return route()
			.path(apiPath, builder1 -> builder1
				.nest(accept(APPLICATION_JSON), builder2 -> builder2
					.GET("", handler::findAll)
					.GET("/{id}", handler::findById)
					.POST("", handler::create)
					.PUT("", handler::update)
					.DELETE("/{id}", handler::deleteById)))
			.build();
	}

}