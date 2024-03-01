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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.article.Article;
import com.xiaoqing.blog.model.article.ArticleDto;
import com.xiaoqing.blog.model.article.IArticleService;
import com.xiaoqing.blog.model.authentication.AuthenticationService;

import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ArticleController {

	private final IArticleService articleService;

	// 新建文章
	@PostMapping("/articles")
	public ResponseEntity<?> createsArticle(@RequestBody Article article) {
		try {
			articleService.createsArticle(article);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 取得所有文章Dto By Pageable，根據分頁信息返回文章Dto的分頁結果
	@GetMapping
	public ResponseEntity<?> getArticles(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "articleId"));
		Page<ArticleDto> articleDtos = articleService.getArticleDtos(pageable);
		return new ResponseEntity<>(articleDtos, HttpStatus.OK);
	}

	// 取得文章 ById，依照文章ID返回文章
	@GetMapping("/{id}")
	public ResponseEntity<?> getArticles(@PathVariable("id") int id) {
		Article article = articleService.getArticlesById(id);
		if (article != null) {
			return new ResponseEntity<>(article, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// 取得特定種類所有文章Dto By ArticleClassId Pageable，根據文章種類的ID及分頁信息返回文章Dto
	@GetMapping("/class/{id}")
	public ResponseEntity<?> getArticlesByClassId(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @PathVariable("id") int id) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "articleId"));
		Page<ArticleDto> articleDtos = articleService.getArticlesByArticleClassId(id, pageable);
		return new ResponseEntity<>(articleDtos, HttpStatus.OK);
	}

	@PutMapping("/articles")
	public ResponseEntity<?> updatesArticles(@RequestBody Article article) {
		try {
			articleService.updatesArticles(article);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 刪除文章 By Id，依照文章ID刪除文章
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletesArticles(@PathVariable("id") int id) {
		try {
			articleService.deletesArticlesById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
