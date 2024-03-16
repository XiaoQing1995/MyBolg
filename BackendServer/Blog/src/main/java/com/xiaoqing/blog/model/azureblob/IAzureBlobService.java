package com.xiaoqing.blog.model.azureblob;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IAzureBlobService {

	String[] upload(MultipartFile multipartFile) throws IOException;

	byte[] getFile(String fileName) throws URISyntaxException;

	List<String> listBlobs();

	Boolean deleteBlob(String blobName);

}