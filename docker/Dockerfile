FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/munchbox.jar /app/munchbox.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/munchbox.jar"]