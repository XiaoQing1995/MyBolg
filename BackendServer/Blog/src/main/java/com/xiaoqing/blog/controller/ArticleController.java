package com.xiaoqing.blog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.article.Article;
import com.xiaoqing.blog.model.article.ArticleDTO;
import com.xiaoqing.blog.model.article.ArticleSummary;
import com.xiaoqing.blog.model.article.IArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
@CrossOrigin("https://happy-meadow-001df7a00.5.azurestaticapps.net")
public class ArticleController {

	private final IArticleService articleService;

	@PostMapping
	public ResponseEntity<?> createArticle(@ModelAttribute ArticleDTO articleDTO) {
		if (articleService.createArticle(articleDTO)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<?> getArticles(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "articleId"));
		Page<ArticleSummary> articles = articleService.getArticles(pageable);
		return new ResponseEntity<>(articles, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getArticleById(@PathVariable("id") int id) {
		Article article = articleService.getArticleById(id);
		if (article != null) {
			return new ResponseEntity<>(article, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/class/{id}")
	public ResponseEntity<?> getArticlesByClassId(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @PathVariable("id") int id) {
		System.out.println("class");
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "articleId"));
		Page<ArticleSummary> articles = articleService.getArticlesByArticleClassId(id, pageable);
		return new ResponseEntity<>(articles, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> updateArticle(@ModelAttribute ArticleDTO articleDTO) {
		if (articleService.updateArticle(articleDTO)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteArticle(@PathVariable("id") int id) {
		try {
			articleService.deleteArticleById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
