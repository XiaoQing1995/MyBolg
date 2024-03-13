package com.xiaoqing.blog.model.article;

import org.springframework.web.multipart.MultipartFile;

import com.xiaoqing.blog.model.articleclass.ArticleClass;

import lombok.Builder;
import lombok.Data;

// Article部分資訊 不需要文章內容、文章種類
@Data
@Builder
public class ArticleDTO {
	private Integer articleId;
	private String articleTitle;
	private String articleContent;
	private String articleDate;
	private String articleImagePath;
	private String articleThumbnailImagePath;
	private MultipartFile  articleFile;
	private Integer articleClassId;
}
