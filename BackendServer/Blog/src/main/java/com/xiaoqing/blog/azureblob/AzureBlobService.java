package com.xiaoqing.blog.azureblob;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import com.xiaoqing.blog.image.IImageService;
import com.xiaoqing.blog.model.article.ArticleRepository;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

@Service
@RequiredArgsConstructor
public class AzureBlobService implements IAzureBlobService {

	private final BlobServiceClient blobServiceClient;

	private final BlobContainerClient blobContainerClient;

	@Override
	public String[] upload(MultipartFile multipartFile) throws IOException {

		// Todo UUID
		String fileExtension = getFileExtension(multipartFile.getOriginalFilename());
		String uniqueFileName = UUID.randomUUID().toString();
		String originalFileName	 = uniqueFileName + "." + fileExtension;
		String thumbnailFileName = uniqueFileName + "_thumb." + fileExtension;

		// 原始圖片
		BlobClient originalBlobClient = blobContainerClient.getBlobClient(originalFileName);
		originalBlobClient.upload(multipartFile.getInputStream(), multipartFile.getSize(), true);
		
		// 縮略圖
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    Thumbnails.of(multipartFile.getInputStream()).size(200, 200).outputFormat(fileExtension).toOutputStream(baos);
	    byte[] thumbnailBytes = baos.toByteArray();
	    BlobClient thumbnailBlobClient = blobContainerClient.getBlobClient(thumbnailFileName);
	    thumbnailBlobClient.upload(new ByteArrayInputStream(thumbnailBytes), thumbnailBytes.length, true);


		return new String[] { originalFileName, thumbnailFileName }; 
	}

	@Override
	public byte[] getFile(String fileName) throws URISyntaxException {

		BlobClient blob = blobContainerClient.getBlobClient(fileName);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		blob.download(outputStream);
		final byte[] bytes = outputStream.toByteArray();
		return bytes;

	}

	@Override
	public List<String> listBlobs() {

		PagedIterable<BlobItem> items = blobContainerClient.listBlobs();
		List<String> names = new ArrayList<String>();
		for (BlobItem item : items) {
			names.add(item.getName());
		}
		return names;

	}

	@Override
	public Boolean deleteBlob(String blobName) {

		BlobClient blob = blobContainerClient.getBlobClient(blobName);
		blob.delete();
		return true;
	}

	private String getFileExtension(String fileName) {
		if (fileName == null) {
			return "";
		}
		int dotIndex = fileName.lastIndexOf(".");
		return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
	}
}
