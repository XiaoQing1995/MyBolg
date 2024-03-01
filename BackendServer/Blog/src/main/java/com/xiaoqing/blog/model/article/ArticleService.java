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

import com.xiaoqing.blog.model.authentication.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService implements IArticleService {

	private final ArticleRepository articleRepository;

	// 新建文章
	@Override
	public void createsArticle(Article article) {
		articleRepository.save(article);
	}

	// 取得所有文章Dto By Pageable，根據分頁信息返回文章並轉為Dto
	@Override
	public Page<ArticleDto> getArticleDtos(Pageable pageable) {
		Page<ArticleDto> articlesDtos = articleRepository.findAll(pageable).map(this::convertToDto);
		return articlesDtos;
	}

	// 取得文章 By Id，依照文章ID返回文章
	@Override
	public Article getArticlesById(int id) {
		Optional<Article> optionalArticle = articleRepository.findById(id);
		if (optionalArticle.isPresent()) {
			return optionalArticle.get();
		}
		return null;

	}

	// 取得所有文章Dto By ArticleClassId Pageable，根據文章種類的ID及分頁信息返回文章Dto的分頁結果
	@Override
	public Page<ArticleDto> getArticlesByArticleClassId(int id, Pageable pageable) {
		Page<ArticleDto> articlesDtos = articleRepository.findByArticleClass_ArticleClassId(id, pageable)
				.map(this::convertToDto);
		return articlesDtos;
	}
	
	// 更新文章
	@Override
	public void updatesArticles (Article article) {
		articleRepository.save(article);
	}

	// 刪除文章 By Id，依照文章ID刪除文章
	@Override
	public void deletesArticlesById(int id) {
		articleRepository.deleteById(id);

	}

	// 將Article轉為ArticleDto (沒有文章內容)
	private ArticleDto convertToDto(Article article) {
		ArticleDto articleDto = new ArticleDto();
		articleDto.setArticleId(article.getArticleId());
		articleDto.setArticleTitle(article.getArticleTitle());
		articleDto.setArticleDate(article.getArticleDate());
		articleDto.setArticleImg(article.getArticleImg());
		articleDto.setArticleClass(article.getArticleClass());
		return articleDto;
	}
	
}
