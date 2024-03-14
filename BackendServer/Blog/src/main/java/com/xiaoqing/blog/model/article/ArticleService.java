package com.xiaoqing.blog.model.article;

import java.io.IOException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoqing.blog.image.IImageService;
import com.xiaoqing.blog.model.articleclass.ArticleClass;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService implements IArticleService {

	private final ArticleRepository articleRepository;
	private final IImageService imageService;

	@Override
	public boolean createArticle(ArticleDTO articleDTO) {
		try {
			String[] imagePaths = imageService.saveImage(articleDTO.getArticleFile());
			String originalImagePath = imagePaths[0]; // 原始圖片路徑
			String thumbnailImagePath = imagePaths[1]; // 縮略圖圖片路徑

			articleRepository.save(Article.builder().articleTitle(articleDTO.getArticleTitle())
					.articleContent(articleDTO.getArticleContent()).articleDate(articleDTO.getArticleDate())
					.articleClass(ArticleClass.builder().articleClassId(articleDTO.getArticleClassId()).build())
					.articleImagePath(originalImagePath).articleThumbnailImagePath(thumbnailImagePath).build());

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Page<Article> getArticles(Pageable pageable) {
		Page<Article> articles = articleRepository.findAll(pageable);
		return articles;
	}

	@Override
	public Article getArticleById(int id) {
		Optional<Article> optionalArticle = articleRepository.findById(id);
		if (optionalArticle.isPresent()) {
			return optionalArticle.get();
		}
		return null;
	}

	@Override
	public Page<Article> getArticlesByArticleClassId(int id, Pageable pageable) {
		Page<Article> articles = articleRepository.findByArticleClass_ArticleClassId(id, pageable);
		return articles;
	}

	@Override
	public boolean updateArticle(ArticleDTO articleDTO) {
		try {
			if (articleDTO.getArticleFile() != null) {
				String[] imagePaths = imageService.saveImage(articleDTO.getArticleFile());
				String originalImagePath = imagePaths[0]; // 原始圖片路徑
				String thumbnailImagePath = imagePaths[1]; // 縮略圖圖片路徑
				articleRepository.save(Article.builder().articleId(articleDTO.getArticleId())
						.articleTitle(articleDTO.getArticleTitle()).articleContent(articleDTO.getArticleContent())
						.articleDate(articleDTO.getArticleDate())
						.articleClass(ArticleClass.builder().articleClassId(articleDTO.getArticleClassId()).build())
						.articleImagePath(originalImagePath).articleThumbnailImagePath(thumbnailImagePath).build());
				imageService.deleteOldImage(articleDTO.getArticleImagePath());
				imageService.deleteOldImage(articleDTO.getArticleThumbnailImagePath());
			} else {
				articleRepository.save(Article.builder().articleId(articleDTO.getArticleId())
						.articleTitle(articleDTO.getArticleTitle()).articleContent(articleDTO.getArticleContent())
						.articleDate(articleDTO.getArticleDate())
						.articleClass(ArticleClass.builder().articleClassId(articleDTO.getArticleClassId()).build())
						.articleImagePath(articleDTO.getArticleImagePath())
						.articleThumbnailImagePath(articleDTO.getArticleThumbnailImagePath()).build());
			}

			return true;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public void deleteArticleById(int id) {
		Optional<Article> article = articleRepository.findById(id);
		
		articleRepository.deleteById(id);

		imageService.deleteOldImage(article.get().getArticleImagePath());
		imageService.deleteOldImage(article.get().getArticleThumbnailImagePath());

	}

}
