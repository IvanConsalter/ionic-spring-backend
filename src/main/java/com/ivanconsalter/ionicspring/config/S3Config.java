package com.ivanconsalter.ionicspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.ivanconsalter.ionicspring.config.property.IonicSpringProperty;

@Configuration
public class S3Config {

	@Autowired
	private IonicSpringProperty ionicSpringProperty;

	@Bean
	public AmazonS3 amazonS3() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(
				ionicSpringProperty.getS3().getAccessKeyId(),
				ionicSpringProperty.getS3().getSecretAccessKey());

		AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.build();

		return amazonS3;
	}

}
