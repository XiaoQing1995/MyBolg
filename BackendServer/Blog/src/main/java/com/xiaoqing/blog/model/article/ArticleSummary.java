package com.xiaoqing.blog.model.article;

import com.xiaoqing.blog.model.articleclass.ArticleClass;

//Article部分資訊 用於顯示部分的文章內容，因為全部文章內容的查詢時間過長
public interface ArticleSummary {
	String getArticleId();
	String getArticleTitle();
	String getArticleSummaryContent();
	String getArticleDate();
	String getArticleThumbnailImagePath();
	ArticleClass getArticleClass();
}
