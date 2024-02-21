package com.xiaoqing.blog.model.article;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService implements IArticleService {
	@Autowired
	ArticleDao articleDao;

	// 取得所有文章Dto By Pageable，根據分頁信息返回文章Dto的分頁結果
	@Override
	public Page<ArticleDto> getArticleDtos(Pageable pageable) {
		Page<Article> articlesPage = articleDao.findAll(pageable);
		List<ArticleDto> articleDtos = articlesPage.getContent().stream().map(this::convertToDto)
				.collect(Collectors.toList());
		return new PageImpl<>(articleDtos, pageable, articlesPage.getTotalElements());
	}

	// 取得文章 By Id，依照文章ID返回文章
	@Override
	public Article getArticlesById(int id) {
		Optional<Article> optionalArticle = articleDao.findById(id);
		if (optionalArticle.isPresent()) {
			return optionalArticle.get();
		}
		return null;

	}
	
	// 取得所有文章Dto By ArticleClassId Pageable，根據文章種類的ID及分頁信息返回文章Dto的分頁結果
	@Override
	public Page<ArticleDto> getArticlesByArticleClassId (int id, Pageable pageable) {
//		Page<Article> articlesPage = articleDao.findByArticleClass_Id(id, pageable);
//		List<ArticleDto> articleDtos = articlesPage.getContent().stream().map(this::convertToDto)
//				.collect(Collectors.toList());
//		return new PageImpl<>(articleDtos, pageable, articlesPage.getTotalElements());
		return null;
	}

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
	
	// 刪除文章 By Id，依照文章ID刪除文章
	@Override
	public void deletesArticlesById (int id) {
		articleDao.deleteById(id);
		
	}

	// 將Article轉為ArticleDto (沒有文章內容、文章種類)
	private ArticleDto convertToDto(Article article) {
		ArticleDto articleDto = new ArticleDto();
		articleDto.setArticle_id(article.getArticle_id());
		articleDto.setArticle_title(article.getArticle_title());
		articleDto.setArticle_date(article.getArticle_date());
		articleDto.setArticle_img(article.getArticle_img());
		return articleDto;
	}
}
