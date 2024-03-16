package com.xiaoqing.blog.model.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class ImageService implements IImageService {

	@Value("${upload-dir}")
	private String UPLOAD_DIR;

	@Override
	public String[] saveImage(MultipartFile file) throws IOException {
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

		String originalImagePath = "/" + originalFileName;
		String thumbnailImagePath = "/" + thumbnailFileName;

		return new String[] { originalImagePath, thumbnailImagePath };
	}

	@Override
	public void deleteOldImage(String imagePath) {
		Path path = Paths.get(UPLOAD_DIR, imagePath);
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getFileExtension(String fileName) {
		if (fileName == null) {
			return "";
		}
		int dotIndex = fileName.lastIndexOf(".");
		return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
	}
}