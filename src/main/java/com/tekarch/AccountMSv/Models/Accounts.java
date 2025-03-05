package com.tekarch.AccountMSv.Models;

import com.tekarch.AccountMSv.CustomAnnotationCrossField.BalanceValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@BalanceValidation // Custom cross-field validation
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @NotNull(message = "{accountNumber.notnull}") // Uses message from properties file
    @Size(min = 10, max = 20, message = "{accountNumber.size}") // Uses message from properties file
    @Column(name = "account_number", nullable = false, unique = true, length = 20)
    private String accountNumber;

    @NotNull(message = "Account type is required.")
    @Size(min = 3, max = 20, message = "Account type must be between 3 and 20 characters.")
    @Column(name = "account_type", nullable = false, length = 20)
    private String accountType;

    @Size(max = 10, message = "Currency can be at most 10 characters.")
    @Column(name = "currency", length = 10, columnDefinition = "VARCHAR(10) DEFAULT 'USD'")
    private String currency; // Defaults to 'USD'

  //  @DecimalMin(value = "0.0", inclusive = false, message = "Balance must be greater than zero.")
    @Column(name = "balance", columnDefinition = "DECIMAL(15,2) DEFAULT 0.0")
    private Double balance; // Balance must be greater than zero

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
