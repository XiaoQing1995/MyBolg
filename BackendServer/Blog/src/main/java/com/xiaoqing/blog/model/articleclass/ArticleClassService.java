package com.xiaoqing.blog.model.articleclass;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleClassService implements IArticleClassService {

	private final ArticleClassRepository articleClassRepository;

	@Override
	public List<ArticleClass> getAllArticleClasses() {
		return articleClassRepository.findAll();
	}

	@Override
	public ArticleClass getArticleClassById(int id) {
		return articleClassRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Article class not found with id: " + id));
	}

	@Override
	public boolean createsArticleClass(ArticleClass articleClass) {
		if (articleClassRepository.findByArticleClassName(articleClass.getArticleClassName()).isPresent()) {
			return false;
		}

		try {
			articleClassRepository.save(articleClass);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updatesArticleClass(ArticleClass articleClass) {
		if (articleClassRepository.findById(articleClass.getArticleClassId()).isEmpty()
				|| articleClassRepository.findByArticleClassName(articleClass.getArticleClassName()).isPresent()) {
			return false;
		}

		try {
			articleClassRepository.save(articleClass);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void deletesArticleClassById(int id) {
		articleClassRepository.deleteById(id);
	}
}
