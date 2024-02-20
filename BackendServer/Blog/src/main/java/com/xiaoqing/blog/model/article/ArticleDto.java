package com.xiaoqing.blog.model.article;

import lombok.Data;

// Article部分資訊 不需要文章內容、文章種類
@Data
public class ArticleDto {
	private Integer article_id;
	private String article_title;
	private String article_date;
	private byte[] article_img;
}
