package cathy.redisspringboot.infra.redis.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//Make a integrated test
@SpringBootTest
class RedisRepositoryTest {
    @Autowired
    RedisRepository redisRepository;

    @Test
    void test() {
        RedisEntity redisEntity = new RedisEntity();

        redisRepository.save(redisEntity);

        // Get 'keyspace:id'
        redisRepository.findById(redisEntity.getId());

        // Get number of key in @RedisHash's keyspace(entity)
        redisRepository.count();

        redisRepository.delete(redisEntity);
    }

}