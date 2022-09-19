FROM openjdk:11
COPY build/libs/github*.jar github.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "github.jar"]