package com.xiaoqing.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.articleclass.ArticleClass;
import com.xiaoqing.blog.model.articleclass.ArticleClassService;

@RestController
public class ArticleClassController {
	@Autowired
	ArticleClassService articleClassService;
	
	@GetMapping("/articleclass")
	public Iterable<ArticleClass> getArticleClassList () {
		Iterable<ArticleClass> articleClassList = articleClassService.getAll();
		return articleClassList;
	}
}
