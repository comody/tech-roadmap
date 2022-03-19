FROM openjdk:11-jre-slim
EXPOSE 8080
ARG JAR_FILE=build/libs/tech-roadmap-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /tech_roadmap.jar
ENTRYPOINT ["java", "-jar", "/tech_roadmap.jar"]