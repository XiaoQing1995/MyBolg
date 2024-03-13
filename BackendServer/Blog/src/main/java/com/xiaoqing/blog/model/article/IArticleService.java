package com.xiaoqing.blog.model.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleService {

	boolean createArticle(ArticleDTO ArticleDTO);
	
	Page<Article> getArticles(Pageable pageable);

	Article getArticleById(int id);
	
	Page<Article> getArticlesByArticleClassId(int id, Pageable pageable);

	boolean updateArticle(ArticleDTO articleDTO);

	void deleteArticleById(int id);

}