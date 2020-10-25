package com.apirest.webflux.exception;

import com.apirest.webflux.dto.support.ResponseMapEntry;

import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * {@link ControllerAdvice} que intercepta as Exceptions de CRUD (Create, Update, Delete e NotFound) e
 * define um codigo HTTP apropriado para elas.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@ControllerAdvice
public class DefaultControllerAdvice /*extends ResponseEntityExceptionHandler*/ {

	private final MessageSource messageSource;

	public DefaultControllerAdvice(MessageSource messageSource) {this.messageSource = messageSource;}

	@ExceptionHandler(CustomRuntimeException.class)
	public ResponseEntity<ResponseMapEntry> exceptions(CustomRuntimeException e) {
		logarExcecaoSemStackTrace(e);
		var message = messageSource.getMessage(e.getValidationMsg().getKey(), e.getParams(), Locale.getDefault());
		return new ResponseEntity<>(ResponseMapEntry.of("errorMsg", message), BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ResponseMapEntry> exceptions(IllegalArgumentException e) {
		logarExcecaoSemStackTrace(e);
		return new ResponseEntity<>(ResponseMapEntry.of("errorMsg", e.getMessage()), BAD_REQUEST);
	}

	@ExceptionHandler(ConversionFailedException.class)
	public ResponseEntity<Exception> conversionFailedException(ConversionFailedException e) {
		logarExcecaoSemStackTrace(e);
		return new ResponseEntity<>(e, INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Throwable> exception(Throwable e) {
		var cause = NestedExceptionUtils.getMostSpecificCause(e);
		logarExcecaoSemStackTrace(cause);
		return new ResponseEntity<>(cause, INTERNAL_SERVER_ERROR);
	}

	//	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(ResponseMapEntry.of("errorMsg", e.getMessage()), headers, status);
	}

	//	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status,
																  WebRequest request) {
		var listErrors = e.getBindingResult()
						  .getFieldErrors()
						  .stream()
						  .map(error -> error.getField() + ": " + error.getDefaultMessage())
						  .sorted()
						  .collect(Collectors.toList());

		return new ResponseEntity<>(ResponseMapEntry.of("errorMsg", e.getBindingResult().getObjectName() + " -> " + listErrors.toString()),
									headers, status);
	}

	private static void logarExcecaoSemStackTrace(Throwable e, String... errorsMsg) {
		var mensagem = errorsMsg.length == 1 ? errorsMsg[0] : e.getMessage();
		esconderStackTrace(e);
		log.error(mensagem, e);
	}

	private static void esconderStackTrace(Throwable e) {
		e.setStackTrace(new StackTraceElement[]{});
	}

}
