package com.xiaoqing.blog.model.article;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<Article, Integer> {
	// 取得文章 By ArticleClass 的 ArticleClass_Id,Pageable
	Page<Article> findByArticleClass_ArticleClassId (int articleClassId, Pageable pageable);
}
