package com.xiaoqing.blog.model.articleclass;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleClassService {

	// 取得所有文章種類
	List<ArticleClass> getArticleClasses();

	// 取得文章種類 By id，依照文章種類ID返回文章種類
	ArticleClass getArticleClassById(int id);

	// 新建文章種類
	void createsArticleClasses(ArticleClass articleClass);

	// 更新文章種類
	void updatesArticleClasses(ArticleClass articleClass);

	// 刪除文章種類 ById，依照文章種類ID刪除文章
	void deletesArticleClassesById(int id);

}