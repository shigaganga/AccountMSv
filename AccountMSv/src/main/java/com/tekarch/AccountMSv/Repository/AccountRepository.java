package com.tekarch.AccountMSv.Repository;
import com.tekarch.AccountMSv.Models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {

}
