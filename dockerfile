# Use uma imagem base com JDK 22
FROM openjdk:22-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Instala o Maven
RUN apt-get update && apt-get install -y wget \
    && wget https://www-us.apache.org/dist/maven/maven-3/3.9.2/binaries/apache-maven-3.9.2-bin.tar.gz \
    && tar -xzf apache-maven-3.9.2-bin.tar.gz -C /opt/ \
    && ln -s /opt/apache-maven-3.9.2/bin/mvn /usr/bin/mvn \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* apache-maven-3.9.2-bin.tar.gz

# Copia o arquivo pom.xml e o código fonte
COPY pom.xml ./
COPY src ./src

# Executa a construção do projeto usando Maven
RUN mvn clean package -DskipTests

# Copia o JAR construído para o contêiner
COPY target/*.jar app.jar

# Define o comando padrão para rodar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
