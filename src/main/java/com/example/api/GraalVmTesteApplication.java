package com.example.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;

import com.azure.security.keyvault.secrets.SecretClient;

@SpringBootApplication
@ImportRuntimeHints(AzureGraalVmHints.AzureRuntimeHintsRegistrar.class)
public class GraalVmTesteApplication {

	private static final Logger log = LoggerFactory.getLogger(GraalVmTesteApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GraalVmTesteApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(SecretClient secretClient) {
		return args -> {
			String nomeDoSegredo = "basileia";
			try {
				String value = secretClient.getSecret(nomeDoSegredo).getValue();
				log.info("✅ Segredo '{}' obtido com sucesso: {}", nomeDoSegredo, value);
			} catch (Exception e) {
				log.error("⚠️ Erro ao ler segredo '{}': {}", nomeDoSegredo, e.getMessage());
			}
		};
	}
}