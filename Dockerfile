FROM openjdk:17-jdk-slim AS builder
WORKDIR /build

COPY src ./src
COPY pom.xml ./pom.xml

RUN apt-get update && apt-get install -y maven
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY log ./log
COPY --from=builder /build/target/minecraft-clone-1.0-SNAPSHOT-jar-with-dependencies.jar .
COPY server-config.json ./server-config.json

RUN mkdir -p skins

EXPOSE 50000/udp
EXPOSE 50001/tcp

CMD ["java", "-jar", "minecraft-clone-1.0-SNAPSHOT-jar-with-dependencies.jar"]