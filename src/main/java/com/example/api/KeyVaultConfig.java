package com.example.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

@Configuration
public class KeyVaultConfig {

	@Value("${AZURE_ENDPOINT}")
	private String vaultUri;

	@Value("${AZURE_TENANT_ID}")
	private String tenantId;

	@Value("${AZURE_CLIENT_ID}")
	private String clientId;

	@Value("${AZURE_CLIENT_SECRET}")
	private String clientSecret;

	@Bean
	ClientSecretCredential clientSecretCredential() {
		return new ClientSecretCredentialBuilder().tenantId(tenantId).clientId(clientId).clientSecret(clientSecret).build();
	}

	@Bean
	SecretClient secretClient(ClientSecretCredential credential) {
		return new SecretClientBuilder().vaultUrl(vaultUri).credential(credential).buildClient();
	}
}
