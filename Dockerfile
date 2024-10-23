FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /skoda

COPY /src/main ./src/main
COPY /pom.xml .

RUN mvn clean package -DskipTests -Dspotless.check.skip

FROM gcr.io/distroless/java21-debian12 AS runtime

ARG JAR_FILE=skoda/target/*.jar

COPY --from=build ${JAR_FILE} skoda.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/skoda.jar"]
