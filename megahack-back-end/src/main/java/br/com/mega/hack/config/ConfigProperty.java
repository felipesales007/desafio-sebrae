package br.com.mega.hack.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "megahack")
public class ConfigProperty {

	private final Security security = new Security();

	public Security getSecurity() {
		return security;
	}

	public static class Security {
		private boolean enableHttps;
		private String secretKey;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public String getSecretKey() {
			return secretKey;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}

	}

}
