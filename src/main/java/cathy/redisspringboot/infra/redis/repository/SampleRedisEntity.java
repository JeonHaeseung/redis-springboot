package cathy.redisspringboot.infra.redis.repository;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "entity", timeToLive = 30)
//Value will be redis keyspace
//timeToLive will be seconds
public class SampleRedisEntity {
    /*Redis Key = keyspace:id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder
    public SampleRedisEntity(Long id){
        this.id = id;
    }
}