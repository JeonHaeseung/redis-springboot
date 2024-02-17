package cathy.redisspringboot.infra.redis.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//Make a integrated test
@SpringBootTest
class SampleRedisRepositoryTest {

    @Autowired
    SampleRedisRepository sampleRedisRepository;

    @Test
    void test() {
        SampleRedisEntity sampleRedisEntity = SampleRedisEntity.builder().build();

        sampleRedisRepository.save(sampleRedisEntity);

        // Get 'keyspace:id'
        sampleRedisRepository.findById(sampleRedisEntity.getId());

        // Get number of key in @RedisHash's keyspace(entity)
        sampleRedisRepository.count();

        sampleRedisRepository.delete(sampleRedisEntity);
    }

}