package de.grado.accountingservice.repository;

import de.grado.accountingservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>
{
}
