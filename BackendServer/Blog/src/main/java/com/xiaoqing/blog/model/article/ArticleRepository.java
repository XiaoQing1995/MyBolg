package com.xiaoqing.blog.model.article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
//	Page<Article> findByArticleClass_ArticleClassId (int articleClassId, Pageable pageable);
	Page<ArticleSummary> findByArticleClass_ArticleClassId (int articleClassId, Pageable pageable);
	
	Page<ArticleSummary> findAllBy(Pageable pageable);
}
