FROM centos:7
RUN yum install -y java-1.8.0-openjdk
WORKDIR /app/
COPY ./target/SpringDemo-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "SpringDemo-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080