package cathy.redisspringboot.infra.redis.repository;

import org.springframework.data.repository.CrudRepository;

public interface SampleRedisRepository extends CrudRepository<SampleRedisEntity, Long> {
}
