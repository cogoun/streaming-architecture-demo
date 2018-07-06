FROM frolvlad/alpine-oraclejdk8
COPY collaboration-rest-server/target/collaboration-rest-server-0.0.1-SNAPSHOT.jar /opt/applications/collaboration-rest-server/
WORKDIR /opt/applications/collaboration-rest-server/
CMD ["java","-jar", "collaboration-rest-server-0.0.1-SNAPSHOT.jar"]
