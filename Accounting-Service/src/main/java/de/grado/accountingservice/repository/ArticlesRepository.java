package de.grado.accountingservice.repository;

import de.grado.accountingservice.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long>
{
    List<Articles> findByArticleNumber(String articleNumber);
}
