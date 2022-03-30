package com.josharnow.newsaggregator.repositories;

import java.util.List;

import com.josharnow.newsaggregator.models.Article;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long>{
  List<Article> findAll();
}
