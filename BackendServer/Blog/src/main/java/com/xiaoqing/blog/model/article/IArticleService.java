package com.xiaoqing.blog.model.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleService {

	// 新建文章
	public boolean createsArticle(ArticleDTO ArticleDTO);

	// 取得所有文章DTO By Pageable，根據分頁信息返回文章DTO的分頁結果
//	public Page<ArticleDTO> getArticleDTOs(Pageable pageable);
	
	public Page<Article> getArticles(Pageable pageable);

	// 取得文章 ById，依照文章ID返回文章
	public Article getArticlesById(int id);

	// 取得特定種類所有文章DTO By ArticleClassId Pageable，根據文章種類的ID及分頁信息返回文章DTO的分頁結果
//	public Page<ArticleDTO> getArticlesDtosByArticleClassId(int id, Pageable pageable);
	
	public Page<Article> getArticlesByArticleClassId(int id, Pageable pageable);

	// 更新文章
	public boolean updatesArticles(ArticleDTO articleDTO);

	// 刪除文章 ById，依照文章ID刪除文章
	public boolean deletesArticlesById(int id);

}