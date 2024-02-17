package cathy.redisspringboot.infra.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RedisRepository extends JpaRepository<RedisEntity, Long> {
}
