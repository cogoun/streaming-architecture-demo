package com.cogoun.streaming.collaboration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@ComponentScan("com.cogoun.streaming.collaboration")
@EnableRedisRepositories(basePackages = "com.cogoun.streaming.collaboration.repository")
public class CollaborationConfiguration {
}
