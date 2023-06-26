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

ENTRYPOINT exec java -jar gapla.jar

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

