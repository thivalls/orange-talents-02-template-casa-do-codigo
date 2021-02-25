package com.zup.cdc.shared.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Constraint(validatedBy = {IfCountryHasStateNotNullValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface IfCountryHasStateNotNull {
    String message() default "The state is required for this country";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();
}
