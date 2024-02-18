package com.xiaoqing.blog.model.articleclass;

import java.util.List;

import com.xiaoqing.blog.model.article.Article;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ArticleClass")
public class ArticleClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleclass_id;
	
	private String articleclass_name;
}
