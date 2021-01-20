package com.covengers.grouping.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

@Configuration
public class EmbeddedRedisConfiguration {

    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void redisServer() throws IOException {

        String arch = System.getProperty("os.arch");
        System.out.println("arch = " + arch);
        String name = System.getProperty("os.name");

        System.out.println("name = " + name);

        if (isArmMac()) {
            redisServer = new RedisServer(getRedisFileForArmMac(), redisPort);
        } else {
            redisServer = new RedisServer(redisPort);
        }

        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

    private boolean isArmMac() {
        return (System.getProperty("os.arch") == "aarch64") &&
                (System.getProperty("os.name") == "Mac OS X");
    }

    private File getRedisFileForArmMac() throws IOException {
        ClassPathResource resource = new ClassPathResource("binary/redis/redis-server-6.0.10-mac-arm64");
        return resource.getFile();
    }
}
