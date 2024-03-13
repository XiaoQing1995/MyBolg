package com.xiaoqing.blog.model.articleclass;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleClassService {

	List<ArticleClass> getAllArticleClasses();

	ArticleClass getArticleClassById(int id);

	boolean createsArticleClass(ArticleClass articleClass);

	boolean updatesArticleClass(ArticleClass articleClass);

	void deletesArticleClassById(int id);

}