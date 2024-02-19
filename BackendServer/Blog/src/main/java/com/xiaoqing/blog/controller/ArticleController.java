package com.xiaoqing.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.article.Article;
import com.xiaoqing.blog.model.article.ArticleService;

@RestController
public class ArticleController {
	@Autowired
	ArticleService articleService;
	
	// 新建文章
	@PostMapping("/articles")
	public ResponseEntity<?> createsArticle (@RequestBody Article article) {
		if (articleService.createsArticle(article)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
