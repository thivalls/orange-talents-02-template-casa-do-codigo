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
@Constraint(validatedBy = {DependentUniqueFieldValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface DependentUniqueField {
    String message() default "{custom.dependent.unique.field.validator}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName();
    String dependentFieldName();
    Class<?> domainClass();
}
