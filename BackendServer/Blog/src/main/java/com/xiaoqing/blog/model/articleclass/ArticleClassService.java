package com.xiaoqing.blog.model.articleclass;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleClassService {
	@Autowired
	ArticleClassDao articleClassDao;
	
	// 取得所有文章種類
	public Page<ArticleClass> getArticleClasses (Pageable pageable) {
		return articleClassDao.findAll(pageable);
	}
	
	// 取得文章種類
	public ArticleClass getArticleClass (int id) {
		Optional<ArticleClass> optionalArticleClass = articleClassDao.findById(id);
		if (optionalArticleClass.isPresent()) {
			return optionalArticleClass.get();
		} else {
			return null;
		}
	}
	
	// 新增文章種類
	public boolean createsArticleClasses (ArticleClass articleClass) {
		try {
			articleClassDao.save(articleClass);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// 更新文章種類
	public boolean updatesArticleClasses (ArticleClass articleClass) {
		try {
			articleClassDao.save(articleClass);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// 刪除文章種類
	public void deletesArticleClasses (int id) {
		articleClassDao.deleteById(id);	
	}

}
