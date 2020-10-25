package com.apirest.webflux.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ValidationMsg {

	ENTITY_ALREADY_PERSIST("entity_wih_id"),
	ENTITY_NO_PERSIST("entity_no_persist");

	private final String key;

}