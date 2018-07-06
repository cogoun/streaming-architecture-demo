# Introduction

This demo will be deployed inside a set of docker containers. In order to perform a deployment of the
full plattform you need to perform the following:

- Install Docker for your OS
- Go to the parent project folder and run

        $ mvn clean install

- Go to the platform folder:

        $ cd collaboration-platform

- Execute docker-compose up in detached mode

        $ docker-compose up -d

# Structure of the platform

Currently the platform consists of the following images:
- collaboration-rest-server
- zookeper
- kafka
- redis

Information on the dependencies and details can be found in [docker-compose.yml](docker-compose.yml).
