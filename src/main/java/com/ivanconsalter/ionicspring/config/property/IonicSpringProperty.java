package com.ivanconsalter.ionicspring.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("ionic-spring")
@Component
public class IonicSpringProperty {
	
	private final Mail mail = new Mail();
	
	private final S3 s3 = new S3();
	
	public Mail getMail() {
		return mail;
	}
	
	public S3 getS3() {
		return s3;
	}
	
	public static class Mail {
		
		private String host;
		private Integer port;
		private String username;
		private String password;
		
		public String getHost() {
			return host;
		}
		
		public void setHost(String host) {
			this.host = host;
		}
		
		public Integer getPort() {
			return port;
		}
		
		public void setPort(Integer port) {
			this.port = port;
		}
		public String getUsername() {
			return username;
		}
		
		public void setUsername(String username) {
			this.username = username;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
	}
	
	public static class S3 {

		private String accessKeyId;
		private String secretAccessKey;
		private String bucket = "ionic-spring-arquivos";

		public String getAccessKeyId() {
			return accessKeyId;
		}

		public void setAccessKeyId(String accessKeyId) {
			this.accessKeyId = accessKeyId;
		}

		public String getSecretAccessKey() {
			return secretAccessKey;
		}

		public void setSecretAccessKey(String secretAccessKey) {
			this.secretAccessKey = secretAccessKey;
		}
		
		public void setBucket(String bucket) {
			this.bucket = bucket;
		}

		public String getBucket() {
			return bucket;
		}

	}

}
