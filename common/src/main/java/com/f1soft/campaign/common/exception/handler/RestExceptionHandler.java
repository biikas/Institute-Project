package com.f1soft.campaign.common.exception.handler;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.GenericResponse;
import com.f1soft.campaign.common.dto.Message;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.IllegalStateException;
import com.f1soft.campaign.common.exception.*;
import com.f1soft.campaign.common.util.MessageComposer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@SuppressWarnings("Duplicates")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ServerResponse serverResponse, HttpStatus httpStatus) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(serverResponse.isSuccess());
        genericResponse.setMessage(serverResponse.getMessage());
        genericResponse.setCode(serverResponse.getCode() != null && serverResponse.getCode().trim().length() > 0 ? serverResponse.getCode() : "0");
        return new ResponseEntity<>(genericResponse, httpStatus);
    }

    private ResponseEntity<Object> buildResponseEntity(GenericResponse genericResponse, HttpStatus httpStatus) {
        return new ResponseEntity<>(genericResponse, httpStatus);
    }

    @ExceptionHandler(ServerException.class)
    protected ResponseEntity<Object> handleServerException(ServerException ex) {
        log.error("Exception message : {}", ex.getServerResponse().getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }

        ServerResponse serverResponse = ex.getServerResponse();

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(serverResponse.isSuccess());
        genericResponse.setMessage(serverResponse.getMessage());
        genericResponse.setProcessingCode(ex.getProcessingCode());

        return new ResponseEntity<>(genericResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(InvalidDataException.class)
    protected ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        return buildResponseEntity(ex.getServerResponse(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        return buildResponseEntity(ex.getServerResponse(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(DataNotFoundException.class)
    protected ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        return buildResponseEntity(ex.getServerResponse(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        return buildResponseEntity(ex.getServerResponse(), HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(ResourceAlreadyExistException.class)
    protected ResponseEntity<Object> handleResourceAlreadyExistException(ResourceAlreadyExistException ex) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        return buildResponseEntity(ex.getServerResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        String messageKey = MsgConstant.INTERNAL_SERVER_ERROR;
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(false);
        serverResponse = MessageComposer.compose(serverResponse, messageKey, MessageComposer.getParameters());
        return buildResponseEntity(serverResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(Exception ex) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        String messageKey = MsgConstant.INTERNAL_SERVER_ERROR;
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(false);
        serverResponse = MessageComposer.compose(serverResponse, messageKey, MessageComposer.getParameters());
        return buildResponseEntity(serverResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(Exception ex) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        String messageKey = MsgConstant.INTERNAL_SERVER_ERROR;
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(false);
        serverResponse = MessageComposer.compose(serverResponse, messageKey, MessageComposer.getParameters());
        return buildResponseEntity(serverResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        Message message = MessageComposer.compose(MsgConstant.DATA_VALIDATION_ERROR, MessageComposer.getParameters());

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(false);
        genericResponse.setMessage(message.getMessage());
        genericResponse.addValidationErrors(ex.getBindingResult().getFieldErrors());

        return buildResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        Message message = MessageComposer.compose(MsgConstant.DATA_VALIDATION_ERROR, MessageComposer.getParameters());

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(false);
        genericResponse.setMessage(message.getMessage());
        genericResponse.addValidationErrors(ex.getConstraintViolations());

        return buildResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(javax.validation.ValidationException.class)
    protected ResponseEntity<Object> handleConstraintvalidation(javax.validation.ValidationException ex) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        String messageKey = MsgConstant.DATA_VALIDATION_ERROR;
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(false);
        serverResponse = MessageComposer.compose(serverResponse, messageKey, MessageComposer.getParameters());
        return buildResponseEntity(serverResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
                                                                          HttpStatus status, WebRequest request) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        String messageKey = MsgConstant.DATA_VALIDATION_ERROR;
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(false);
        serverResponse = MessageComposer.compose(serverResponse, messageKey, MessageComposer.getParameters());
        return buildResponseEntity(serverResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<Object> handleDataAccessException(DataAccessException ex) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        String messageKey = MsgConstant.INTERNAL_SERVER_ERROR;
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(false);
        serverResponse = MessageComposer.compose(serverResponse, messageKey, MessageComposer.getParameters());
        return buildResponseEntity(serverResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        String messageKey = MsgConstant.INTERNAL_SERVER_ERROR;
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(false);
        serverResponse = MessageComposer.compose(serverResponse, messageKey, MessageComposer.getParameters());
        return buildResponseEntity(serverResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        String messageKey = MsgConstant.DATA_VALIDATION_ERROR;
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(false);
        serverResponse = MessageComposer.compose(serverResponse, messageKey, MessageComposer.getParameters());
        return buildResponseEntity(serverResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {
        log.error("Exception message : {}", ex.getMessage());

        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        String messageKey = ex.getMessage();
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(false);
        serverResponse = MessageComposer.compose(serverResponse, messageKey, MessageComposer.getParameters());
        return buildResponseEntity(serverResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handleDataAccessException(AccessDeniedException ex) {
        log.error("Exception message : {}", ex.getMessage());
        if (log.isDebugEnabled()) {
            log.error("Exception : ", ex);
        }
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(false);
        serverResponse.setMessage("Access Denied");
        return this.buildResponseEntity(serverResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
