package com.xiaoqing.blog.model.article;

import java.io.IOException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoqing.blog.azureblob.IAzureBlobService;
import com.xiaoqing.blog.image.IImageService;
import com.xiaoqing.blog.model.articleclass.ArticleClass;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService implements IArticleService {

	private final ArticleRepository articleRepository;
	private final IAzureBlobService iAzureBlobService;

	@Override
	public boolean createArticle(ArticleDTO articleDTO) {
		try {
			String[] imagePaths = iAzureBlobService.upload(articleDTO.getArticleFile());
			String originalImage = imagePaths[0]; // 原始圖片路徑
			String thumbnailImage = imagePaths[1]; // 縮略圖圖片路徑

			articleRepository.save(Article.builder()
					.articleTitle(articleDTO.getArticleTitle())
					.articleSummaryContent(articleDTO.getArticleSummaryContent())
					.articleContent(articleDTO.getArticleContent())
					.articleDate(articleDTO.getArticleDate())
					.articleClass(ArticleClass.builder()
							.articleClassId(articleDTO.getArticleClassId())
							.build())
					.articleOriginalImage(originalImage)
					.articleThumbnailImage(thumbnailImage).build());

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Page<ArticleSummary> getArticles(Pageable pageable) {
		Page<ArticleSummary> articles = articleRepository.findAllBy(pageable);
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
	public Page<ArticleSummary> getArticlesByArticleClassId(int id, Pageable pageable) {
		Page<ArticleSummary> articles = articleRepository.findByArticleClass_ArticleClassId(id, pageable);
		return articles;
	}

	@Override
	public boolean updateArticle(ArticleDTO articleDTO) {
		try {
			if (articleDTO.getArticleFile() != null) {
				String[] imagePaths = iAzureBlobService.upload(articleDTO.getArticleFile());
				String originalImage = imagePaths[0]; // 原始圖片路徑
				String thumbnailImage = imagePaths[1]; // 縮略圖圖片路徑
				articleRepository.save(Article.builder()
						.articleId(articleDTO.getArticleId())
						.articleTitle(articleDTO.getArticleTitle())
						.articleSummaryContent(articleDTO.getArticleSummaryContent())
						.articleContent(articleDTO.getArticleContent())
						.articleDate(articleDTO.getArticleDate())
						.articleClass(ArticleClass.builder()
								.articleClassId(articleDTO.getArticleClassId())
								.build())
						.articleOriginalImage(originalImage)
						.articleThumbnailImage(thumbnailImage).build());
				iAzureBlobService.deleteBlob(articleDTO.getArticleOriginalImage());
				iAzureBlobService.deleteBlob(articleDTO.getArticleThumbnailImage());
			} else {
				articleRepository.save(Article.builder()
						.articleId(articleDTO.getArticleId())
						.articleTitle(articleDTO.getArticleTitle())
						.articleSummaryContent(articleDTO.getArticleSummaryContent())
						.articleContent(articleDTO.getArticleContent())
						.articleDate(articleDTO.getArticleDate())
						.articleClass(ArticleClass.builder()
								.articleClassId(articleDTO.getArticleClassId())
								.build())
						.articleOriginalImage(articleDTO.getArticleOriginalImage())
						.articleThumbnailImage(articleDTO.getArticleThumbnailImage()).build());
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

		iAzureBlobService.deleteBlob(article.get().getArticleOriginalImage());
		iAzureBlobService.deleteBlob(article.get().getArticleThumbnailImage());

	}

}
