package com.xiaoqing.blog.model.articleclass;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ArticleClassRepository extends JpaRepository<ArticleClass, Integer> {
	Optional<ArticleClass> findByArticleClassName(String articleClassName);
}
