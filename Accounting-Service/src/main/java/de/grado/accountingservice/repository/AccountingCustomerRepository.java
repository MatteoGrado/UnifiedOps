package de.grado.accountingservice.repository;

import de.grado.accountingservice.model.Accounting_Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountingCustomerRepository extends JpaRepository<Accounting_Customer, Long>
{
    Optional<Accounting_Customer> findByCustomerId(Long customerId);
}
