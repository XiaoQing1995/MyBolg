package com.xiaoqing.blog.model.article;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<Article, Integer> {

}
