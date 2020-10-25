package com.apirest.webflux.service.support;

import com.apirest.webflux.document.support.EntityPersistable;
import com.apirest.webflux.exception.EntityCreateException;
import com.apirest.webflux.exception.ValidationMsg;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CrudImplService<R extends ReactiveMongoRepository<T, String>, T extends EntityPersistable> implements CrudService<T> {

	protected final R repository;

	protected final Class<T> entityClass;

	public CrudImplService(@NonNull R repository, @NonNull Class<T> entityClass) {
		this.repository = repository;
		this.entityClass = entityClass;
	}

	@Override
	public Mono<T> insert(T entity) {
		if (!entity.isNew()) {
			throw new EntityCreateException(ValidationMsg.ENTITY_ALREADY_PERSIST, entity.getClass().getSimpleName(), entity.getId());
		}
		logInfo("Criando novo {}", entityClass.getSimpleName());
		return repository.insert(entity);
	}

	@Override
	public Mono<T> save(T entity) {
		if (entity.isNew()) {
			throw new EntityCreateException(ValidationMsg.ENTITY_NO_PERSIST, entity.getClass().getSimpleName());
		}
		logInfo("Atualizando {}: {}", entityClass.getSimpleName(), entity.getId());
		return repository.save(entity);
	}

	@Override
	public Mono<T> findById(String id) {
		logInfo("Buscando {} com ID: {}", entityClass.getSimpleName(), id);
		return repository.findById(id);
	}

	@Override
	public Flux<T> findAll() {
		logInfo("Buscando todos {}", entityClass.getSimpleName() + "s");
		return repository.findAll();
	}

	@Override
	public Mono<Void> deleteById(@NonNull String id) {
		logInfo("Removendo {} com ID: {}", entityClass.getSimpleName(), id);
		repository.deleteById(id);
		return Mono.empty();
	}

	@Override
	public Class<T> getEntityClass() {
		return this.entityClass;
	}

	protected static void logInfo(@NonNull String format, Object... args) {
		log.info(format + " (" + LocalDateTime.now() + ")", args);
	}

}