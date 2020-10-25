package com.apirest.webflux.dto.support;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AuditableDto<U> extends PersistableDto {

	private static final long serialVersionUID = 141481953116476081L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private U createdBy;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime createdDate;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private U lastModifiedBy;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime lastModifiedDate;

}