package com.xiaoqing.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Configuration
public class AzureBlobConfig {

	@Value("${azure.storage.connection.string}")
	private String connectionString;

	@Value("${azure.storage.container.name}")
	private String containerName;

	@Bean
	BlobServiceClient blobServiceClient() {

		BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString)
				.buildClient();

		return blobServiceClient;

	}

	@Bean
	BlobContainerClient blobContainerClient() {

		BlobContainerClient blobContainerClient = blobServiceClient().getBlobContainerClient(containerName);

		return blobContainerClient;

	}
}
