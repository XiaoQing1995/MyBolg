package com.xiaoqing.blog.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.articleclass.ArticleClass;
import com.xiaoqing.blog.model.articleclass.IArticleClassService;
import com.xiaoqing.blog.model.authentication.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articleclasses")
public class ArticleClassController {
	
	private final IArticleClassService articleClassService;

	// 取得所有文章種類
	@GetMapping
	public ResponseEntity<?> getArticleClasses() {
		List<ArticleClass> articleClass = articleClassService.getArticleClasses();
		return new ResponseEntity<>(articleClass, HttpStatus.OK);
	}

	// 取得文章種類 By id，依照文章種類ID返回文章種類
	@GetMapping("/{id}")
	public ResponseEntity<?> getArticleClasses(@PathVariable("id") int id) {
		ArticleClass articleClass = articleClassService.getArticleClassById(id);
		if (articleClass != null) {
			return new ResponseEntity<>(articleClass, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// 新建文章種類
	@PostMapping
	public ResponseEntity<?> createsArticleClasses(@RequestBody ArticleClass articleClass) {
		try {
			articleClassService.createsArticleClasses(articleClass);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 更新文章種類
	@PutMapping
	public ResponseEntity<?> updatesArticleClasses(@RequestBody ArticleClass articleClass) {
		try {
			articleClassService.updatesArticleClasses(articleClass);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	// 刪除文章種類 ById，依照文章種類ID刪除文章
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletesArticleClasses(@PathVariable("id") int id) {
		try {
			articleClassService.deletesArticleClassesById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
