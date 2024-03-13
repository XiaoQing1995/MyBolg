package com.xiaoqing.blog.model.article;

import com.xiaoqing.blog.model.articleclass.ArticleClass;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Article")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleId;
	
	private String articleTitle;
	private String articleContent;
	private String articleDate;
	private String articleImagePath;
	private String articleThumbnailImagePath;
	
//	@Column(name = "articleclass_id")
//	private int articleclass_id;
	
	
	@ManyToOne
	@JoinColumn(name = "articleClassId")
	private ArticleClass articleClass;
}
