package com.tekarch.AccountMSv.CustomAnnotationCrossField;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // Applied at the class level
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BalanceValidator.class) // Specifies the validator class
public @interface BalanceValidation {
    String message() default "Balance cannot be negative for savings accounts"; // Default error message
    Class<?>[] groups() default {}; // Used for grouping constraints
    Class<? extends Payload>[] payload() default {}; // Used to carry additional data
}
