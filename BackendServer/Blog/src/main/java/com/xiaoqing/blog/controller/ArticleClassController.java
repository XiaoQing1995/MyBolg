package com.xiaoqing.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.articleclass.ArticleClass;
import com.xiaoqing.blog.model.articleclass.IArticleClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articleclasses")
@CrossOrigin("https://happy-meadow-001df7a00.5.azurestaticapps.net")
public class ArticleClassController {

	private final IArticleClassService articleClassService;

	@GetMapping
	public ResponseEntity<?> getAllArticleClasses() {
		List<ArticleClass> articleClass = articleClassService.getAllArticleClasses();
		return new ResponseEntity<>(articleClass, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ArticleClass> getArticleClassById(@PathVariable("id") int id) {
		ArticleClass articleClass = articleClassService.getArticleClassById(id);
		return new ResponseEntity<>(articleClass, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createArticleClass(@RequestBody ArticleClass articleClass) {
		if (articleClassService.createsArticleClass(articleClass)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping
	public ResponseEntity<?> updateArticleClass(@RequestBody ArticleClass articleClass) {
		if (articleClassService.updatesArticleClass(articleClass)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteArticleClassById(@PathVariable("id") int id) {
		try {
			articleClassService.deletesArticleClassById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
