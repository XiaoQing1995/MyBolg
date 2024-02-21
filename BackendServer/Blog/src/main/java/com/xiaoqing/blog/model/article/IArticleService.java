package com.xiaoqing.blog.model.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleService {

	// 新建文章
	void createsArticle(Article article);

	// 取得所有文章Dto By Pageable，根據分頁信息返回文章Dto的分頁結果
	Page<ArticleDto> getArticleDtos(Pageable pageable);

	// 取得文章 ById，依照文章ID返回文章
	Article getArticlesById(int id);

	// 取得特定種類所有文章Dto By ArticleClassId Pageable，根據文章種類的ID及分頁信息返回文章Dto的分頁結果
	Page<ArticleDto> getArticlesByArticleClassId(int id, Pageable pageable);

	// 更新文章
	void updatesArticles(Article article);

	// 刪除文章 ById，依照文章ID刪除文章
	void deletesArticlesById(int id);

}