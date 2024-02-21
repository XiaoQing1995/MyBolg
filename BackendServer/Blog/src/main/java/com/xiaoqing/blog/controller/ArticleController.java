package com.xiaoqing.blog.controller;

import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.article.Article;
import com.xiaoqing.blog.model.article.ArticleDto;
import com.xiaoqing.blog.model.article.IArticleService;

@RestController
public class ArticleController {
	@Autowired
	IArticleService articleService;
	
	// 取得所有文章Dto By Pageable，根據分頁信息返回文章Dto的分頁結果
	@GetMapping("/articles")
	public ResponseEntity<?> getArticles (@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "articleId"));
		Page<ArticleDto> articleDtos = articleService.getArticleDtos(pageable);
		return new ResponseEntity<>(articleDtos, HttpStatus.OK);
	}
	
	// 新建文章
	@PostMapping("/articles")
	public ResponseEntity<?> createsArticle (@RequestBody Article article) {
		if (articleService.createsArticle(article)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
}
