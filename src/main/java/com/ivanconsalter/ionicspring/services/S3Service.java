package com.ivanconsalter.ionicspring.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ivanconsalter.ionicspring.config.property.IonicSpringProperty;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private IonicSpringProperty ionicSpringProperty;
	
	@Autowired
	private AmazonS3 amazonS3 ;

	public void uploadFile(String localFilePath) {
		try {
			File file = new File(localFilePath);
			LOG.info("Iniciando upload");
			amazonS3.putObject(new PutObjectRequest(ionicSpringProperty.getS3().getBucket(), "teste.jpg", file));
			LOG.info("Upload finalizado");
		}
		catch (AmazonServiceException e) {
			LOG.info("AmazonServiceException: " + e.getErrorMessage());
			LOG.info("Status code: " + e.getErrorCode());
		}
		catch (AmazonClientException e) {
			LOG.info("AmazonClientException: " +  e.getMessage());
		}
	}
}
