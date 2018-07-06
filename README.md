# Introduction

This is a demo application that will be used to demonstrate various concepts
that are relevant to a modern streaming architecture such as:

- the use of a events for communication between applications [Event Sourcing](https://martinfowler.com/eaaDev/EventSourcing.html)
by utilising an event bus [Kafka](http://kafka.apache.org/).
- the use of a database such as [Redis](https://redis.io/) to keep the latest instance of an application.
- to demonstrate the implementation of [CQRS](https://martinfowler.com/bliki/CQRS.html).

The application will be implemented with [Spring Boot](https://spring.io/projects/spring-boot).

Docker will be used in order to setup and provision all the necessary images. Refer [here](collaboration-platform/REEADME.md) for more
information.

Architecture details can be found [here](collaboration-docs/architecture/sad.md). Architecture is
captured using the C4 model.