package com.apirest.webflux.dto.support;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class PersistableDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

}