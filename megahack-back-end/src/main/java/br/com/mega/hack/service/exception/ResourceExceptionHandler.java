package br.com.mega.hack.service.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest req) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not found", e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(OAuth2Exception.class)
	public ResponseEntity<StandardError> oAuth2Exception(OAuth2Exception e, HttpServletRequest req) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), "Unathorized", e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}	
	
	@ExceptionHandler(TokenInvalidException.class)
	public ResponseEntity<StandardError> tokenInvalidException(TokenInvalidException e, HttpServletRequest req) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), "Invalid Token", e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}	
	
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public ResponseEntity<StandardError> customOauthException(InternalAuthenticationServiceException e, HttpServletRequest req) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), "Method not allowed", e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<StandardError> dataIntegrety(HttpRequestMethodNotSupportedException e, HttpServletRequest req) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Method not allowed", e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest req) {	
		ValidationError validationError = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", "Error during validation the field", req.getRequestURI());
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			validationError.addError(f.getField(),f.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
	}
	
//	@ExceptionHandler(AuthorizationException.class)
//	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest req) {
//		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso negado", e.getMessage(), req.getRequestURI());
//		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
//	}
//	
//	@ExceptionHandler(FileException.class)
//	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest req) {
//		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de Dados", e.getMessage(), req.getRequestURI());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//	}
//	
//	@ExceptionHandler(AmazonServiceException.class)
//	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest req) {
//		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
//		StandardError error = new StandardError(System.currentTimeMillis(), code.value(), "Erro Amazon Service", e.getMessage(), req.getRequestURI());
//		return ResponseEntity.status(code.value()).body(error);
//	}
//	
//	@ExceptionHandler(AmazonClientException.class)
//	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest req) {
//		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de Dados", e.getMessage(), req.getRequestURI());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//	}
//	
//	@ExceptionHandler(AmazonS3Exception.class)
//	public ResponseEntity<StandardError> amazons3(AmazonS3Exception e, HttpServletRequest req) {
//		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de Dados", e.getMessage(), req.getRequestURI());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//	}

}
