FROM openjdk:8-jre-alpine
EXPOSE 8080
ADD target/json-crud-0.0.1-SNAPSHOT.jar json-crud-0.0.1.jar
RUN mkdir -p /opt/jsondb -p && chmod 777 /opt/jsondb 
COPY src/main/resources/db.json /opt/jsondb
ENTRYPOINT ["java","-jar","/json-crud-0.0.1.jar"]
