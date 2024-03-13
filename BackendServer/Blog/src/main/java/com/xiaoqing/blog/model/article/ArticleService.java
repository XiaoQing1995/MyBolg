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

	// 新建文章
	@Override
	public boolean createsArticle(ArticleDTO articleDTO) {
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

//	// 取得所有文章DTO By Pageable，根據分頁信息返回文章並轉為Dto
//	@Override
//	public Page<ArticleDTO> getArticleDTOs(Pageable pageable) {
//		Page<ArticleDTO> articlesDtos = articleRepository.findAll(pageable).map(this::convertToDTO);
//		return articlesDTOs;
//	}

	// 取得所有文章 By Pageable
	@Override
	public Page<Article> getArticles(Pageable pageable) {
		Page<Article> articles = articleRepository.findAll(pageable);
		return articles;
	}

	// 取得文章 By Id，依照文章ID返回文章
	@Override
	public Article getArticlesById(int id) {
		Optional<Article> optionalArticle = articleRepository.findById(id);
		if (optionalArticle.isPresent()) {
			return optionalArticle.get();
		}
		return null;

	}

//	// 取得所有文章DTO By ArticleClassId Pageable，根據文章種類的ID及分頁信息返回文章Dto的分頁結果
//	@Override
//	public Page<ArticleDTO> getArticlesDtosByArticleClassId(int id, Pageable pageable) {
//		Page<ArticleDTO> articlesDTOs = articleRepository.findByArticleClass_ArticleClassId(id, pageable)
//				.map(this::convertToDTO);
//		return articlesDTOs;
//	}

	@Override
	public Page<Article> getArticlesByArticleClassId(int id, Pageable pageable) {
		Page<Article> articles = articleRepository.findByArticleClass_ArticleClassId(id, pageable);
		return articles;
	}

	// 更新文章
	@Override
	public boolean updatesArticles(ArticleDTO articleDTO) {
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

//		articleRepository.save(articleDTO);
	}

	// 刪除文章 By Id，依照文章ID刪除文章
	@Override
	public boolean deletesArticlesById(int id) {
		Optional<Article> article = articleRepository.findById(id);
		if (article.isPresent()) {
			deleteOldImage(article.get().getArticleImagePath());
			deleteOldImage(article.get().getArticleThumbnailImagePath());
			articleRepository.deleteById(id);
			
			return true;
		} 
		return false;

	}

//	// 將Article轉為ArticleDTO (沒有文章內容)
//	private ArticleDTO convertToDTO(Article article) {
//		ArticleDTO articleDTO = ArticleDTO.builder()
//				.articleId(article.getArticleId())
//				.articleTitle(article.getArticleTitle())
//				.articleDate(article.getArticleDate())
//				.articleClass(article.getArticleClass())
//				.articleImagePath(article.getArticleImagePath())
//				.build();
//		return articleDTO;
//	}

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
