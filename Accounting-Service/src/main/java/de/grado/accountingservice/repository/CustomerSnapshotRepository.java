package de.grado.accountingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSnapshotRepository extends JpaRepository<de.grado.accountingservice.model.CustomerSnapshot, String>
{
}
