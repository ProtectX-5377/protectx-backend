FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Instalar Maven en la imagen
RUN apt-get update && apt-get install -y maven

COPY pom.xml .
COPY src ./src

RUN mvn -q -e -DskipTests clean package

FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]
