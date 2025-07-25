# 🚀 GraalVM Spring Boot Application

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![GraalVM](https://img.shields.io/badge/GraalVM-Native-FF6600?style=for-the-badge&logo=graalvm&logoColor=white)](https://www.graalvm.org/)
[![Azure](https://img.shields.io/badge/Azure-Key_Vault-0078D4?style=for-the-badge&logo=microsoft-azure&logoColor=white)](https://azure.microsoft.com/en-us/services/key-vault/)
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

> 🎯 **Aplicação Spring Boot moderna otimizada para GraalVM Native Image com integração Azure Key Vault**

## 📋 Sobre o Projeto

Esta aplicação demonstra como criar uma **API REST moderna** usando as mais recentes tecnologias Java, incluindo:

- ✨ **Java 21** - Versão LTS mais recente com recursos avançados
- 🚀 **Spring Boot 3.5.0** - Framework mais popular para APIs Java
- ⚡ **GraalVM Native Image** - Compilação nativa para performance extrema
- 🔐 **Azure Key Vault** - Gerenciamento seguro de secrets
- 🐳 **Docker** - Containerização para deployment

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| ![Java](https://img.shields.io/badge/-Java_21-ED8B00?style=flat&logo=openjdk&logoColor=white) | **21** | Linguagem de programação principal |
| ![Spring Boot](https://img.shields.io/badge/-Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white) | **3.5.0** | Framework para APIs REST |
| ![GraalVM](https://img.shields.io/badge/-GraalVM-FF6600?style=flat&logo=graalvm&logoColor=white) | **Native** | Compilação nativa de alta performance |
| ![Azure](https://img.shields.io/badge/-Azure_SDK-0078D4?style=flat&logo=microsoft-azure&logoColor=white) | **4.9.4** | Integração com Azure Key Vault |
| ![Maven](https://img.shields.io/badge/-Maven-C71A36?style=flat&logo=apache-maven&logoColor=white) | **3.x** | Gerenciamento de dependências |
| ![Docker](https://img.shields.io/badge/-Docker-2496ED?style=flat&logo=docker&logoColor=white) | **Latest** | Containerização |

## 🔧 Pré-requisitos

### Para Execução JVM:
- ☕ **Java 21** ou superior
- 📦 **Maven 3.6+** (ou use `./mvnw`)

### Para Execução Native:
- 🔥 **GraalVM 22.3+** com Native Image
- 📦 **Maven 3.6+**
- 🐳 **Docker** (para builds containerizados)

## 🚀 Como Executar

### 💻 Modo JVM (Desenvolvimento)

```bash
# Clone o repositório
git clone <url-do-repositorio>
cd graalVM-teste

# Execute com Maven Wrapper
./mvnw spring-boot:run

# Ou compile e execute
./mvnw clean package
java -jar target/graalVM-teste-0.0.1-SNAPSHOT.jar
```

### ⚡ Modo GraalVM Native (Produção)

#### 🏗️ Compilação Local
```bash
# Compile para executável nativo
./mvnw clean native:compile -Pnative

# Execute o binário nativo
./target/graalVM-teste
```

#### 🐳 Build com Docker (Recomendado)
```bash
# Build da imagem nativa
./mvnw spring-boot:build-image -Pnative

# Execute o container
docker run --rm -p 8080:8080 graalVM-teste:0.0.1-SNAPSHOT
```

### 🧪 Testes

```bash
# Testes tradicionais
./mvnw test

# Testes em Native Image
./mvnw test -PnativeTest
```

## 📊 Comparação de Performance

| Métrica | JVM | GraalVM Native |
|---------|-----|----------------|
| 🚀 **Startup** | ~2-3s | ~50ms |
| 💾 **Memória** | ~150MB | ~50MB |
| 📦 **Tamanho** | ~43MB | ~13MB |
| ⚡ **Cold Start** | Lento | Instantâneo |

## 🔐 Configuração Azure Key Vault

A aplicação está configurada para integração com Azure Key Vault. Configure as seguintes variáveis:

```bash
# Variáveis de ambiente necessárias
export AZURE_CLIENT_ID=your-client-id
export AZURE_CLIENT_SECRET=your-client-secret
export AZURE_TENANT_ID=your-tenant-id
export AZURE_KEYVAULT_URI=https://your-keyvault.vault.azure.net/
```

## 🐳 Docker

### 🏃‍♂️ Execução com JVM
```bash
docker build -f Dockerfile.jvm -t graalvm-app:jvm .
docker run -p 8080:8080 graalvm-app:jvm
```

### ⚡ Execução Native
```bash
docker build -f Dockerfile.native -t graalvm-app:native .
docker run -p 8080:8080 graalvm-app:native
```

### 🎼 Docker Compose
```bash
docker-compose up -d
```

## 📡 Endpoints Disponíveis

| Endpoint | Método | Descrição |
|----------|--------|-----------|
| `/actuator/health` | GET | Health check da aplicação |
| `/actuator/info` | GET | Informações da aplicação |
| `/actuator/metrics` | GET | Métricas da aplicação |

## 🛠️ Desenvolvimento

### 📁 Estrutura do Projeto
```
src/
├── main/
│   ├── java/com/example/api/
│   │   ├── GraalVmTesteApplication.java    # 🚀 Classe principal
│   │   ├── KeyVaultConfig.java             # 🔐 Config Azure Key Vault
│   │   └── AzureGraalVmHints.java          # ⚙️ Hints GraalVM
│   └── resources/
│       ├── META-INF/native-image/          # 📋 Configs GraalVM
│       ├── application.properties          # ⚙️ Configurações
│       └── logback-spring.xml              # 📝 Logs
└── test/                                   # 🧪 Testes
```

### 🔧 Configurações GraalVM

O projeto inclui configurações otimizadas para GraalVM Native Image:
- **Reflection Config** - Para Spring Framework
- **Resource Config** - Para recursos estáticos
- **Serialization Config** - Para JSON/XML
- **JNI Config** - Para integrações nativas

## 📚 Documentação Adicional

- [🍃 Spring Boot Reference](https://docs.spring.io/spring-boot/3.5.0/reference/html/)
- [⚡ GraalVM Native Image](https://docs.spring.io/spring-boot/3.5.0/reference/packaging/native-image/)
- [🔐 Azure Key Vault SDK](https://docs.microsoft.com/en-us/java/api/overview/azure/security-keyvault-secrets-readme)
- [🐳 Spring Boot Docker](https://spring.io/guides/gs/spring-boot-docker/)

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 📞 Suporte

Se você encontrar algum problema ou tiver dúvidas:
- 🐛 [Abra uma issue](../../issues)
- 💬 Entre em contato com a equipe de desenvolvimento

---

<div align="center">

**⭐ Se este projeto foi útil, considere dar uma estrela!**

Made with ❤️ using Java 21 + Spring Boot + GraalVM

</div>