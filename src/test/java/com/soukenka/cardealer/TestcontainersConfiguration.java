package com.soukenka.cardealer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    private static final MariaDBContainer<?> MARIA_DB_CONTAINER = new MariaDBContainer<>(DockerImageName.parse("mariadb:11.7"))
            .withReuse(true);

    @Bean
    @ServiceConnection
    MariaDBContainer<?> mariaDbContainer() {
        return MARIA_DB_CONTAINER;
    }

}
