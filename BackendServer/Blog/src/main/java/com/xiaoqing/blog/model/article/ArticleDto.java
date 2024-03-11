package com.xiaoqing.blog.model.article;

import com.xiaoqing.blog.model.articleclass.ArticleClass;

import lombok.Builder;
import lombok.Data;

// Article部分資訊 不需要文章內容、文章種類
@Data
@Builder
public class ArticleDto {
	private Integer articleId;
	private String articleTitle;
	private String articleDate;
	private byte[] articleImg;
	private ArticleClass articleClass;
}
