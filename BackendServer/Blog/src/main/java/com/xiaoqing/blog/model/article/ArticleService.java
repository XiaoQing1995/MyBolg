package com.xiaoqing.blog.model.article;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoqing.blog.model.articleclass.ArticleClass;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService implements IArticleService {

	private final ArticleRepository articleRepository;

	@Value("${upload-dir}")
	private String UPLOAD_DIR;

	private String originalImagePath;
	private String thumbnailImagePath;

	@Override
	public boolean createArticle(ArticleDTO articleDTO) {
		try {
			saveImage(articleDTO.getArticleFile());

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
				saveImage(articleDTO.getArticleFile());
				articleRepository.save(Article.builder().articleId(articleDTO.getArticleId())
						.articleTitle(articleDTO.getArticleTitle()).articleContent(articleDTO.getArticleContent())
						.articleDate(articleDTO.getArticleDate())
						.articleClass(ArticleClass.builder().articleClassId(articleDTO.getArticleClassId()).build())
						.articleImagePath(originalImagePath).articleThumbnailImagePath(thumbnailImagePath).build());
				deleteOldImage(articleDTO.getArticleImagePath());
				deleteOldImage(articleDTO.getArticleThumbnailImagePath());
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

		deleteOldImage(article.get().getArticleImagePath());
		deleteOldImage(article.get().getArticleThumbnailImagePath());

		articleRepository.deleteById(id);
	}

	private void saveImage(MultipartFile file) throws IOException {

		Path uploadPath = Paths.get(UPLOAD_DIR);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		String fileExtension = getFileExtension(file.getOriginalFilename());
		String uniqueFileName = UUID.randomUUID().toString();
		String originalFileName = uniqueFileName + "." + fileExtension;
		String thumbnailFileName = uniqueFileName + "_thumb." + fileExtension;

		Path originalFilePath = uploadPath.resolve(originalFileName);
		file.transferTo(originalFilePath.toFile());

		Path thumbnailPath = uploadPath.resolve(thumbnailFileName);
		Thumbnails.of(originalFilePath.toFile()).size(200, 200).toFile(thumbnailPath.toFile());

		originalImagePath = "/" + originalFileName;
		thumbnailImagePath = "/" + thumbnailFileName;

	}

	private void deleteOldImage(String imagePath) {
		Path path = Paths.get(UPLOAD_DIR + imagePath);
		System.out.println(UPLOAD_DIR + imagePath);
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
			// 可以记录日志或者做其他错误处理
		}
	}

	private String getFileExtension(String fileName) {
		if (fileName == null) {
			return null;
		}
		int dotIndex = fileName.lastIndexOf(".");
		return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
	}

}
