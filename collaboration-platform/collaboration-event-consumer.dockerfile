FROM frolvlad/alpine-oraclejdk8
COPY collaboration-event-consumer/target/collaboration-event-consumer-0.0.1-SNAPSHOT.jar /opt/applications/collaboration-event-consumer/
WORKDIR /opt/applications/collaboration-event-consumer/
CMD ["java","-jar", "collaboration-event-consumer-0.0.1-SNAPSHOT.jar"]
