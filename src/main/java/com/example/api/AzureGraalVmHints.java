package com.example.api;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

public class AzureGraalVmHints {

	// Private constructor to hide the implicit public one
	private AzureGraalVmHints() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	static class AzureRuntimeHintsRegistrar implements RuntimeHintsRegistrar {

		@Override
		public void registerHints(@NonNull RuntimeHints hints, @Nullable ClassLoader classLoader) {
			// Configurações básicas de reflexão para Azure
			hints.reflection().registerType(ClientSecretCredential.class).registerType(ClientSecretCredentialBuilder.class)
					.registerType(SecretClient.class).registerType(SecretClientBuilder.class).registerType(KeyVaultSecret.class);

			// Classes Azure Core e Identity problemáticas - com todas as categorias
			registerTypeWithAllAccess(hints, "com.azure.security.keyvault.secrets.implementation.models.SecretBundle");
			registerTypeWithAllAccess(hints, "com.azure.security.keyvault.secrets.implementation.models.SecretItem");
			registerTypeWithAllAccess(hints, "com.azure.security.keyvault.secrets.implementation.models.SecretAttributes");
			registerTypeWithAllAccess(hints, "com.azure.security.keyvault.secrets.implementation.models.SecretProperties");
			registerTypeWithAllAccess(hints, "com.azure.security.keyvault.secrets.implementation.models.DeletionRecoveryLevel");
			registerTypeWithAllAccess(hints, "com.azure.core.util.ExpandableStringEnum");
			registerTypeWithAllAccess(hints, "com.azure.identity.implementation.RegionalAuthority");
			registerTypeWithAllAccess(hints, "com.azure.identity.implementation.IdentityClientOptions");
			registerTypeWithAllAccess(hints, "com.azure.identity.CredentialBuilderBase");
			registerTypeWithAllAccess(hints, "com.azure.identity.AadCredentialBuilderBase");

			// Classes Jackson para desserialização
			registerTypeWithAllAccess(hints, "com.fasterxml.jackson.databind.ObjectMapper");
			registerTypeWithAllAccess(hints, "com.fasterxml.jackson.databind.DeserializationContext");
			registerTypeWithAllAccess(hints, "com.fasterxml.jackson.databind.deser.BeanDeserializer");
			registerTypeWithAllAccess(hints, "com.fasterxml.jackson.databind.deser.BeanDeserializerBase");
			registerTypeWithAllAccess(hints, "com.fasterxml.jackson.databind.deser.DefaultDeserializationContext");

			// SLF4J e Logging
			hints.reflection().registerType(TypeReference.of("org.slf4j.helpers.Reporter"))
					.registerType(TypeReference.of("org.slf4j.LoggerFactory"))
					.registerType(TypeReference.of("org.slf4j.impl.StaticLoggerBinder"))
					.registerType(TypeReference.of("ch.qos.logback.classic.Logger"))
					.registerType(TypeReference.of("ch.qos.logback.core.Appender"));

			// Netty/Reactor
			hints.reflection().registerType(TypeReference.of("reactor.netty.http.client.HttpClient"))
					.registerType(TypeReference.of("io.netty.channel.nio.NioEventLoopGroup"))
					.registerType(TypeReference.of("io.netty.buffer.ByteBuf"))
					.registerType(TypeReference.of("io.netty.handler.codec.http.HttpResponse"));

			// Recursos adicionais necessários
			hints.resources().registerPattern("META-INF/native-image/**").registerPattern("META-INF/services/**")
					.registerPattern("*.properties").registerPattern("*.yml").registerPattern("*.yaml");

			// JNI para bibliotecas nativas
			hints.jni().registerType(TypeReference.of("com.azure.core.implementation.http.HttpClientProviders"))
					.registerType(TypeReference.of("com.azure.core.http.netty.NettyAsyncHttpClientProvider"));
		}

		private void registerTypeWithAllAccess(RuntimeHints hints, String className) {
			hints.reflection().registerType(TypeReference.of(className),
					hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
							MemberCategory.INVOKE_DECLARED_METHODS, MemberCategory.INVOKE_PUBLIC_METHODS, MemberCategory.DECLARED_FIELDS,
							MemberCategory.PUBLIC_FIELDS, MemberCategory.DECLARED_CLASSES, MemberCategory.PUBLIC_CLASSES));
		}
	}
}