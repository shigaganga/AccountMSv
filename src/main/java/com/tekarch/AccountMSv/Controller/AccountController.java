package com.tekarch.AccountMSv.Controller;
import com.tekarch.AccountMSv.Services.AccountServiceImpl;
import com.tekarch.AccountMSv.Models.Accounts;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
    @RequestMapping ("/accounts")
    public class AccountController {

        @Autowired
        private AccountServiceImpl accountsService;

        @GetMapping
        public ResponseEntity<List<Accounts>> getAllAccounts() {
            return new ResponseEntity<>(accountsService.getAllAccounts(), HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<Accounts> createAccount(@Valid @RequestBody Accounts account) {
            Accounts createdAccount = accountsService.addAccount(account);
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        }

        @PutMapping
        public ResponseEntity<Accounts> updateAccount(@RequestBody Accounts account) {
            Accounts createdAccount = accountsService.updateAccount(account);
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Accounts> getAccountById(@PathVariable Long id) {
            Accounts account = accountsService.getAccountById(id);
            if (account != null) {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        @DeleteMapping("/{id}")
        // ResponseEntity<String> is used to return an HTTP response with a
        // body of type String and an appropriate HTTP status code.
        public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
            if(!accountsService.getAccountById(id).getAccountId().equals(0L)) {
                //0L might be used as a default value to represent a non-existent or invalid AccountId.
                accountsService.deleteAccount(id);
                return ResponseEntity.ok("Account deleted successfully.");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found.");
        }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Handle field-level errors
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        // Handle class-level (global) validation errors
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
