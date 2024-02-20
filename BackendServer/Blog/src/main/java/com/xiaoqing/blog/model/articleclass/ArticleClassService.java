package com.xiaoqing.blog.model.articleclass;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleClassService implements IArticleService {
	@Autowired
	ArticleClassDao articleClassDao;

	// 取得所有文章種類
	@Override
	public List<ArticleClass> getArticleClasses() {
		return articleClassDao.findAll();
	}

	// 取得文章種類 By id，依照文章種類ID返回文章種類
	@Override
	public ArticleClass getArticleClassById(int id) {
		Optional<ArticleClass> optionalArticleClass = articleClassDao.findById(id);
		if (optionalArticleClass.isPresent()) {
			return optionalArticleClass.get();
		}
		return null;

	}

	// 新建文章種類
	@Override
	public boolean createsArticleClasses(ArticleClass articleClass) {
		try {
			articleClassDao.save(articleClass);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 更新文章種類
	@Override
	public boolean updatesArticleClasses(ArticleClass articleClass) {
		try {
			articleClassDao.save(articleClass);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 刪除文章種類 ById，依照文章種類ID刪除文章
	@Override
	public void deletesArticleClassesById(int id) {
		articleClassDao.deleteById(id);
	}

}
