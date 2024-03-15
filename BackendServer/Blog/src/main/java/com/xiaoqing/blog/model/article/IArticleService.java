package com.xiaoqing.blog.model.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleService {

	boolean createArticle(ArticleDTO ArticleDTO);
	
	Page<ArticleSummary> getArticles(Pageable pageable);

	Article getArticleById(int id);
	
	Page<ArticleSummary> getArticlesByArticleClassId(int id, Pageable pageable);

	boolean updateArticle(ArticleDTO articleDTO);

	void deleteArticleById(int id);

}