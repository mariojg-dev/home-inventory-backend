# Stage 1: Build the application
FROM eclipse-temurin:21-jdk AS build
ARG BUILD_DATE
ARG VERSION=1.0.0
LABEL maintainer="mariojg.dev@gmail.com" \
      org.opencontainers.image.name="Home Inventory Backend" \
      org.opencontainers.image.title="Home Inventory Backend" \
      org.opencontainers.image.description="A home inventory management backend built with Spring Boot" \
      org.opencontainers.image.version=$VERSION \
      org.opencontainers.image.created=$BUILD_DATE \
      org.opencontainers.image.licenses="MIT" \
      org.opencontainers.image.source="https://github.com/mariojg-dev/home-inventory-backend"

WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s --start-period=5s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]