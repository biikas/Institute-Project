package com.f1soft.campaign.common.util;

import com.f1soft.campaign.common.dto.ModelBase;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import lombok.*;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ApiResponseHelper extends ModelBase {
    private List<ApiError> apiErrors;


    private void addSubResponse(ApiError sub) {
        if (apiErrors == null) {
            apiErrors = new ArrayList<>();
        }
        apiErrors.add(sub);
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getField(),
                fieldError.getDefaultMessage());
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubResponse(new ApiValidationError(object, field, rejectedValue, message));
    }

    private void addValidationError(String field, String message) {
        addSubResponse(new ApiValidationError(field, message));
    }

    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getMessage());
    }

    public abstract class ApiError {

    }


    @Data
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    public class ApiValidationError extends ApiError {
        private String object;
        private String field;
        private Object rejectedValue;
        private String message;

        public ApiValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}


class LowerCaseClassNameResolver extends TypeIdResolverBase {

    @Override
    public String idFromValue(Object value) {
        return value.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}

