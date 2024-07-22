FROM openjdk:17-jdk-slim
WORKDIR /app

COPY out/artifacts/MinecraftServer_jar/MinecraftServer.jar .

EXPOSE 50000

CMD ["java", "-jar", "MinecraftServer.jar"]