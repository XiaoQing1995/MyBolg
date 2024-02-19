package com.xiaoqing.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.articleclass.ArticleClass;
import com.xiaoqing.blog.model.articleclass.ArticleClassService;

@RestController
public class ArticleClassController {
	@Autowired
	ArticleClassService articleClassService;

	// 取得所有文章種類
	@GetMapping("/articleclasses")
	public ResponseEntity<?> getArticleClassList() {
		List<ArticleClass> articleClassList = articleClassService.getArticleClasses();
		return new ResponseEntity<>(articleClassList, HttpStatus.OK);
	}
	
	// 新增文章種類
	@PostMapping("/articleclasses")
	public ResponseEntity<?> createsArticleClass (@RequestBody ArticleClass articleClass) {
		if (articleClassService.createsArticleClasses(articleClass)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
