package com.xiaoqing.blog.image;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {

	String[]  saveImage(MultipartFile file) throws IOException;

	void deleteOldImage(String imagePath);

}
