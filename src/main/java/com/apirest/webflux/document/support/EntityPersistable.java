package com.apirest.webflux.document.support;

import org.springframework.data.domain.Persistable;

public interface EntityPersistable extends Persistable<String> {

	@Override
	default boolean isNew() {
		return getId() == null || getId().isBlank();
	}

}