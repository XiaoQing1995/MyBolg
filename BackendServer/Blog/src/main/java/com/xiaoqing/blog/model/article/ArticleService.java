package com.xiaoqing.blog.model.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService implements IAccountArticleService {
	@Autowired
	ArticleDao articleDao;
	
	// 新建文章
	@Override
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
