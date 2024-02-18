package cathy.redisspringboot.infra.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    /* Use Lettuce for Redis(Jedis has been deprecated since Springboot 2.0) */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        /* Most basic configuration */
        //return new LettuceConnectionFactory(host, port);

        /* Detailed configuration */
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(host, port);

        LettuceClientConfiguration clientConfig =
                LettuceClientConfiguration.builder()
                        .commandTimeout(Duration.ofSeconds(1)) //No longer than 1 second
                        .shutdownTimeout(Duration.ZERO) //Immediately shutdown after application shutdown
                        .build();
        return new LettuceConnectionFactory(redisConfig, clientConfig);
    }
}
