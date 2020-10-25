package com.apirest.webflux.service.support;

import com.apirest.webflux.document.support.EntityPersistable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CrudService<T extends EntityPersistable> {

	Class<T> getEntityClass();

	Mono<T> insert(T entity);

	Mono<T> save(T entity);

	Mono<T> findById(String id);

	Flux<T> findAll();

	Mono<Void> deleteById(String id);

}