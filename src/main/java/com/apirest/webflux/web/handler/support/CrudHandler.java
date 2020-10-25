package com.apirest.webflux.web.handler.support;

import com.apirest.webflux.document.support.EntityPersistable;
import com.apirest.webflux.service.support.CrudService;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.NonNull;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

public abstract class CrudHandler<S extends CrudService<T>, T extends EntityPersistable> {

	protected final S service;

	public CrudHandler(@NonNull S service) {
		this.service = service;
	}

	public Mono<ServerResponse> findAll(@NonNull ServerRequest request) {
		return ok().contentType(MediaType.TEXT_EVENT_STREAM).body(service.findAll(), service.getEntityClass());
	}

	public Mono<ServerResponse> findById(@NonNull ServerRequest request) {
		return ok().contentType(APPLICATION_JSON).body(service.findById(request.pathVariable("id")), service.getEntityClass());
	}

	public Mono<ServerResponse> deleteById(@NonNull ServerRequest request) {
		return ok().body(service.deleteById(request.pathVariable("id")), Void.class);
	}

	public Mono<ServerResponse> create(@NonNull ServerRequest request) {
		return ok().contentType(APPLICATION_JSON)
				   .body(fromPublisher(request.bodyToMono(service.getEntityClass()).flatMap(service::insert), service.getEntityClass()));
	}

	public Mono<ServerResponse> update(@NonNull ServerRequest request) {
		return ok().contentType(APPLICATION_JSON)
				   .body(fromPublisher(request.bodyToMono(service.getEntityClass()).flatMap(service::save), service.getEntityClass()));
	}

}