# ===== Etapa 1: Build =====
FROM gradle:8.9-jdk21 AS builder
WORKDIR /app

# Copiamos solo los archivos de Gradle primero para caché de dependencias
COPY build.gradle settings.gradle gradlew gradlew.bat ./
COPY gradle ./gradle

# Descargamos dependencias (mejor caché)
RUN ./gradlew dependencies --no-daemon || true

# Copiamos el resto del proyecto
COPY . .

# Generamos el JAR
RUN ./gradlew clean bootJar --no-daemon

# ===== Etapa 2: Imagen final =====
FROM eclipse-temurin:21-jre
WORKDIR /app

# Instala cliente de PostgreSQL para pg_isready
RUN apt-get update && apt-get install -y postgresql-client && rm -rf /var/lib/apt/lists/*

# Copiamos el JAR desde la etapa builder
COPY --from=builder /app/build/libs/*.jar app.jar

# Copiamos el script para esperar la DB y le damos permisos
COPY docker/wait-for-db.sh /app/wait-for-db.sh
RUN chmod +x /app/wait-for-db.sh

# Puerto que expone la app
EXPOSE 9090

# Comando para iniciar la app: espera a la DB y luego arranca Spring Boot
ENTRYPOINT ["/bin/sh", "-c", "/app/wait-for-db.sh && exec java -jar app.jar"]

