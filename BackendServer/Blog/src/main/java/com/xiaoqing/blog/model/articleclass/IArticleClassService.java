package com.xiaoqing.blog.model.articleclass;

import java.util.List;

public interface IArticleClassService {

	List<ArticleClass> getAllArticleClasses();

	ArticleClass getArticleClassById(int id);

	boolean createsArticleClass(ArticleClass articleClass);

	boolean updatesArticleClass(ArticleClass articleClass);

	void deletesArticleClassById(int id);

}