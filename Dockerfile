FROM maven:3.8.5-openjdk-17 AS MAVEN_BUILD
ARG BUILD_ID=0.0.1-SNAPSHOT
USER root
RUN mkdir -p /root/.m2 \
    && mkdir /root/.m2/repository
COPY settings.xml /root/.m2
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean package -DskipTests -Dbuild.version=${BUILD_ID} -ntp


FROM openjdk:17
USER root
ARG BUILD_ID=0.0.1-SNAPSHOT
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/gapla-${BUILD_ID}.jar /app/gapla.jar

EXPOSE 8080

# Set environment variables in the container
ENV DB_HOST=185.215.187.165
ENV DB_NAME=gapla
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=Wwox2287!
ENV DB_PORT=5432

# Add the application.properties file to the container
COPY src/main/resources/application1.properties /app/application.properties

ENTRYPOINT exec java -Dspring.config.location=file:/app/application.properties -jar gapla.jar

#FROM maven:3.8.5-openjdk-17 AS MAVEN_BUILD
#ARG BUILD_ID=0.0.1-SNAPSHOT
#USER root
#RUN mkdir -p /root/.m2 \
#    && mkdir /root/.m2/repository
#COPY settings.xml /root/.m2
#COPY pom.xml /build/
#COPY src /build/src/
#WORKDIR /build/
#RUN mvn clean package -DskipTests -Dbuild.version=${BUILD_ID} -ntp
#
#
#FROM openjdk:17
#USER root
#ARG BUILD_ID=0.0.1-SNAPSHOT
#WORKDIR /app
#COPY --from=MAVEN_BUILD /build/target/gapla-${BUILD_ID}.jar /app/gapla.jar
#
#EXPOSE 8080
#
#ENTRYPOINT exec java -jar gapla.jar
#
#FROM openjdk:20
#USER root
#ENV DB_HOST=185.215.187.165
#ENV DB_NAME=gapla
#ENV DB_USERNAME=postgres
#ENV DB_PASSWORD=Wwox2287!
#ENV DB_PORT=5432
#
#ADD gapla-0.0.1-SNAPSHOT.jar gapla.jar
#ENTRYPOINT exec java -Dspring.profiles.active=cloud-test -jar gapla.jar

# Create group/user bloomuser and set ownership to /app
#RUN addgroup -S bloomuser && adduser --disabled-password --gecos "" --ingroup "bloomuser" --home /app --no-create-home --uid 1001 bloomuser
#RUN chown -R bloomuser:bloomuser /app
#USER bloomuser

# ENTRYPOINT ["java", "-XX:+PrintFlagsFinal", "-jar", "-Dserver.port=8080", "koloritmarketplace.jar"]
# FROM openjdk:12
# #COPY /target/userservice.jar /usr/src/userservice.jar
# ADD ac.jar ac.jar
# ENTRYPOINT ["java","-jar","/ac.jar"]

# FROM openjdk:17-alpine
# USER root
# #COPY /target/userservice.jar /usr/src/userservice.jar
# ADD koloritmarketplace-0.0.1-SNAPSHOT.jar koloritmarketplace.jar
# ENTRYPOINT exec java -Dspring.profiles.active=cloud-test -jar koloritmarketplace.jar

