package com.nikosera.common.exception.handler;

import com.nikosera.common.dto.GenericResponse;
import com.nikosera.common.exception.*;
import com.nikosera.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        log.error("Runtime exception : {}", ex);
        GenericResponse response = new GenericResponse(false, "System error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NoResultFoundException.class)
    public ResponseEntity<Object> handleNoResultException(NoResultFoundException ex) {
        log.error("No result exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<Object> handleUnsupportedMediaException(HttpMediaTypeNotSupportedException ex) {
//        log.error("Http media type not supported exception : {}", ex);
//        GenericResponse response = new GenericResponse(false, "Method not allowed");
//        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
//    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidArgumentException(MethodArgumentNotValidException ex) {
        log.error("Method argument not valid exception : {}", ex);
        GenericResponse response = new GenericResponse(false, "Invalid request parameters");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleMissingBodyException(HttpMessageNotReadableException ex) {
        log.error("Http message not readable exception : {}", ex);
        GenericResponse response = new GenericResponse(false, "Invalid request parameters");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceAlreadyExistException.class)
    public ResponseEntity<Object> handleDuplicateResourceException(ResourceAlreadyExistException ex) {
        log.error("Resource already exist exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.IM_USED);
    }

    @ExceptionHandler(value = LimitedPrivilegeException.class)
    public ResponseEntity<Object> handleNoPermissionException(LimitedPrivilegeException ex) {
        log.error("Limited privilege exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException ex) {
        log.error("Invalid request exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = JwtTokenMalformedException.class)
    public ResponseEntity<Object> handleJwtTokenMalformedException(JwtTokenMalformedException ex) {
        log.error("Jwt token malformed exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = JwtTokenMissingException.class)
    public ResponseEntity<Object> handleJwtTokenMissingException(JwtTokenMissingException ex) {
        log.error("Jwt token missing exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedLoginException(UnauthorizedException ex) {
        log.error("Unauthorized exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = JwtTokenExpiredException.class)
    public ResponseEntity<Object> handleJwtTokenExpiredException(JwtTokenExpiredException ex) {
        log.error("Jwt token expired exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserTemporarilyBlockedException.class)
    protected ResponseEntity<Object> handleUserTemporarilyBlockedException(UserTemporarilyBlockedException ex) {
        log.error("User temporarily blocked exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserPermanentBlockedException.class)
    protected ResponseEntity<Object> handleUserPermanentlyBlockedException(UserPermanentBlockedException ex) {
        log.error("User permanent blocked exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        log.error("User not found exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(AccessDeniedException ex) {
        log.error("Access denied exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error("Method argument type mismatch exception : {}", ex);
        GenericResponse response = new GenericResponse(false, "Invalid request parameters");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IndistinctDataException.class)
    protected ResponseEntity<Object> handleIndistinctDataException(IndistinctDataException ex) {
        log.error("Indistinct data exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidDataException.class)
    protected ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex) {
        log.error("Invalid data exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidTokenException.class)
    protected ResponseEntity<Object> handleInvalidTokenException(InvalidTokenException ex) {
        log.error("Invalid token exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(QrGenerationException.class)
    protected ResponseEntity<Object> handleQrGenerationException(QrGenerationException ex) {
        log.error("Qr generation exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenGenerationException.class)
    protected ResponseEntity<Object> handleTokenGenerationException(TokenGenerationException ex) {
        log.error("Token generation exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleUsernameNotFound(UsernameNotFoundException ex) {
        log.error("Username exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles case where Table exist though the search parameter or the table is empty
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = EmptyResultException.class)
    public ResponseEntity<Object> handleEmptyResultException(EmptyResultException ex) {
        log.error("Empty : {}", ex.getMessage());
        GenericResponse response = new GenericResponse(true, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(SystemException.class)
    protected ResponseEntity<Object> handlesSystemException(SystemException ex) {
        log.error("System exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidOTPException.class)
    protected ResponseEntity<Object> handlesInvalidOTPException(InvalidOTPException ex) {
        log.error("System exception : {}", ex);
        GenericResponse response = new GenericResponse(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("ConstraintViolationException : {}", ex.getMessage());
        GenericResponse response = new GenericResponse(false, "Invalid request parameters");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
