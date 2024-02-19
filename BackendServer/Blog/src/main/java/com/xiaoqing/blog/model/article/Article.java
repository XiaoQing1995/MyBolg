package com.xiaoqing.blog.model.article;

import com.xiaoqing.blog.model.articleclass.ArticleClass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Article")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer article_id;
	
	private String article_title;
	private String article_content;
	private String article_date;
	private byte[] article_img;
	
	@Column(name = "articleclass_id")
	private int articleclass_id;
	
	
	@ManyToOne
	@JoinColumn(name = "articleclass_id",insertable = false,updatable = false)
	private ArticleClass articleClass;
}
