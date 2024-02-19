package com.xiaoqing.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.articleclass.ArticleClass;
import com.xiaoqing.blog.model.articleclass.ArticleClassService;

@RestController
public class ArticleClassController {
	@Autowired
	ArticleClassService articleClassService;

	// 取得所有文章種類
	@GetMapping("/articleclasses")
	public ResponseEntity<Page<ArticleClass>> getArticleClassPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<ArticleClass> articleClassPage = articleClassService.getArticleClasses(pageable);
		return new ResponseEntity<>(articleClassPage, HttpStatus.OK);
	}
	
	// 取得文章種類
	@GetMapping("/articleclasses/{id}")
	public ResponseEntity<?> getArticleClasses (@PathVariable("id") int id) {
		ArticleClass articleClass = articleClassService.getArticleClass(id);
		if (articleClass != null) {
			return new ResponseEntity<>(articleClass, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	// 新增文章種類
	@PostMapping("/articleclasses")
	public ResponseEntity<?> createsArticleClasses(@RequestBody ArticleClass articleClass) {
		if (articleClassService.createsArticleClasses(articleClass)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	// 更新文章種類
	@PutMapping("/articleclasses")
	public ResponseEntity<?> updatesArticleClasses(@RequestBody ArticleClass articleClass) {
		System.out.println("test");
		if (articleClassService.updatesArticleClasses(articleClass)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// 刪除文章種類
	@DeleteMapping("/articleclasses/{id}")
	public ResponseEntity<?> deletesArticleClasses(@PathVariable("id") int id) {
		try {
			articleClassService.deletesArticleClasses(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
