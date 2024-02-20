package com.xiaoqing.blog.model.articleclass;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleService {

	// 取得所有文章種類
	Page<ArticleClass> getArticleClasses(Pageable pageable);

	// 取得文章種類
	ArticleClass getArticleClass(int id);

	// 新建文章種類
	boolean createsArticleClasses(ArticleClass articleClass);

	// 更新文章種類
	boolean updatesArticleClasses(ArticleClass articleClass);

	// 刪除文章種類
	void deletesArticleClasses(int id);

}