package com.apirest.webflux.dto.support;

import com.apirest.webflux.enums.support.UserType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends PersistableDto {


	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private UserType userType;

}