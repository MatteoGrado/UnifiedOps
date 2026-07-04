package de.grado.accountingservice.repository;

import de.grado.accountingservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, BigInteger>
{
    Optional<Article> findByArticleNumber(String articleNumber);
}
