package com.ali.stockexchangemanagement.common.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.math.BigDecimal;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = PositivePriceValidator.class)
@Documented
public @interface PositivePrice {

    String message() default "must be positive" +
            " found: ${validatedValue}";

    Class<?>[] groups() default {};

    Class<? extends BigDecimal>[] payload() default {};

}
