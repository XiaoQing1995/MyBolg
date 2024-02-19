package com.xiaoqing.blog.model.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
	@Autowired
	ArticleDao articleDao;
	
	public boolean createsArticle(Article article) {
		try {
			articleDao.save(article);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
