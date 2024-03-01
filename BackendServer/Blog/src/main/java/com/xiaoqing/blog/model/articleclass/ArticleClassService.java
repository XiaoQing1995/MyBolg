package com.xiaoqing.blog.model.articleclass;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoqing.blog.model.authentication.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleClassService implements IArticleClassService {

	private final ArticleClassRepository articleClassRepository;

	// 取得所有文章種類
	@Override
	public List<ArticleClass> getArticleClasses() {
		return articleClassRepository.findAll();
	}

	// 取得文章種類 By id，依照文章種類ID返回文章種類
	@Override
	public ArticleClass getArticleClassById(int id) {
		Optional<ArticleClass> optionalArticleClass = articleClassRepository.findById(id);
		if (optionalArticleClass.isPresent()) {
			return optionalArticleClass.get();
		}
		return null;

	}

	// 新建文章種類
	@Override
	public void createsArticleClasses(ArticleClass articleClass) {
		articleClassRepository.save(articleClass);
	}

	// 更新文章種類
	@Override
	public void updatesArticleClasses(ArticleClass articleClass) {
		articleClassRepository.save(articleClass);
	}

	// 刪除文章種類 ById，依照文章種類ID刪除文章
	@Override
	public void deletesArticleClassesById(int id) {
		articleClassRepository.deleteById(id);
	}

}
