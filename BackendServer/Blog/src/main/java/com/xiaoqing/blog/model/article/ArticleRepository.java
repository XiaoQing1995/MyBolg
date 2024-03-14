package com.xiaoqing.blog.model.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	Page<Article> findByArticleClass_ArticleClassId (int articleClassId, Pageable pageable);
	
}
