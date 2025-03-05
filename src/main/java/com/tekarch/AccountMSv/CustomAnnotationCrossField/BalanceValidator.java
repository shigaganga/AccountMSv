package com.tekarch.AccountMSv.CustomAnnotationCrossField;

import jakarta.validation.ConstraintValidator;
import com.tekarch.AccountMSv.Models.Accounts;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BalanceValidator implements ConstraintValidator<BalanceValidation, Accounts> {

    @Override
    public boolean isValid(Accounts account, ConstraintValidatorContext context) {
        if (account == null) {
            return true;  // Skip validation if account is null
        }

        // Check if account type is "SAVINGS" and balance is negative
        if ("SAVINGS".equalsIgnoreCase(account.getAccountType()) && account.getBalance() < 0) {
            context.disableDefaultConstraintViolation();

            // Add custom validation message
            context.buildConstraintViolationWithTemplate("Savings account cannot have a negative balance.")
                    .addConstraintViolation();
            return false;  // Invalid balance for Savings account
        }else{
            return true;
        }


       // return true;  // Valid balance for non-Savings accounts
    }
}


