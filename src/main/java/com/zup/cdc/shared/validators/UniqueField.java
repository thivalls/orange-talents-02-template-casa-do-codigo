package com.zup.cdc.shared.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = {UniqueFieldValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueField {
    String message() default "{custom.unique.field.validator}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName();
    Class<?> domainClass();
}
