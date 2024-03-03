FROM maven:3.9.6-eclipse-temurin-21 AS buildOne
ARG PROFILE_NAME
WORKDIR /app
COPY . /app
RUN echo "Java build profile: $PROFILE_NAME"
RUN mvn clean package -P$PROFILE_NAME

FROM eclipse-temurin:21.0.2_13-jdk-jammy AS buildTwo
ARG PROFILE_NAME
WORKDIR /app
ARG JAR_FILE=/app/target/*.jar
COPY --from=buildOne ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:21.0.2_13-jdk-jammy
ARG PROFILE_NAME
WORKDIR /app
COPY --from=buildTwo /app/dependencies/ ./
COPY --from=buildTwo /app/snapshot-dependencies/ ./
COPY --from=buildTwo /app/spring-boot-loader/ ./
COPY --from=buildTwo /app/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher", "-Dspring.profiles.active=$PROFILE_NAME"]