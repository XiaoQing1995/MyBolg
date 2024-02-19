package com.xiaoqing.blog.model.articleclass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleClassService {
	@Autowired
	ArticleClassDao articleClassDao;
	
	// 取得所有文章種類
	public List<ArticleClass> getArticleClasses () {
		return articleClassDao.findAll();
	}
	
	// 新增文章種類
	public boolean createsArticleClasses (ArticleClass articleClass) {
		try {
			articleClassDao.save(articleClass);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
