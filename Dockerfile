FROM openjdk:17-jdk-slim AS builder
WORKDIR /build

COPY src ./src
COPY libs/linux ./libs

RUN javac -cp 'libs/*' -d out $(find src -name '*.java')

RUN jar --create --file MinecraftServer.jar --manifest /dev/null -C out .
RUN echo "Main-Class: fr.math.minecraft.ServerMain" > MANIFEST.MF && jar --update --file MinecraftServer.jar --manifest MANIFEST.MF
RUN jar --update --file MinecraftServer.jar -C libs .


FROM openjdk:17-jdk-slim
WORKDIR /app

COPY log ./log
COPY --from=builder /build/MinecraftServer.jar .

EXPOSE 50000

CMD ["java", "-jar", "MinecraftServer.jar"]