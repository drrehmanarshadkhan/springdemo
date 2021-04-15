#FROM artifactory.io.thehut.local:5000/centos:7.20181207.0000
FROM centos:7
RUN yum install -y java-11-openjdk.x86_64 && yum clean all
WORKDIR /app/
COPY ./target/SpringDemo-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "SpringDemo-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080