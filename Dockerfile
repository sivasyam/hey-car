#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM java:8
COPY --from=build /home/app/target/hey-car-1.0.jar /usr/local/lib/hey-car-1.0.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/hey-car-1.0.jar"]
