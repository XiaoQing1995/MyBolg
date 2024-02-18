package com.xiaoqing.blog.model.articleclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleClassService {
	@Autowired
	ArticleClassDao articleClassDao;
	
	public Iterable<ArticleClass> getAll () {
		return articleClassDao.findAll();
	}

}
