package cathy.redisspringboot.infra.redis.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SampleRedisTemplateTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /* Test 5 data types of redis */
    @Test
    void testStrings() {
        // given
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String key = "stringKey";

        // when
        valueOperations.set(key, "redis");

        // then
        Object value = valueOperations.get(key);
        assertThat(value).isEqualTo("redis");

        redisTemplate.delete("stringKey");
    }

    @Test
    void testSet() {
        // given
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        String key = "setKey";

        // when
        setOperations.add(key, "a", "a", "a", "b", "b");

        // then
        Set<Object> members = setOperations.members(key);
        Long size = setOperations.size(key);

        assertThat(members).containsOnly("a", "b");
        assertThat(size).isEqualTo(2);

        redisTemplate.delete("setKey");
    }

    @Test
    void testSortedSet() {
        // given
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        String key = "zSetKey";

        // when
        zSetOperations.add(key, "c", 1L);
        zSetOperations.add(key, "b", 2L);
        zSetOperations.add(key, "a", 3L);

        // then
        Set<Object> members = zSetOperations.rangeByScore(key, 1L, 2L);
        Long size = zSetOperations.size(key);

        assertThat(members).containsOnly("c", "b");
        assertThat(size).isEqualTo(3);

        redisTemplate.delete("setKey");
    }

    @Test
    void testList() {
        // give
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        String key = "listKey";
        Long index = 1L;

        // when
        listOperations.rightPush(key, "hello");
        listOperations.rightPush(key, "world");
        Object value = listOperations.index(key, 1);
        Long size = listOperations.size(key);

        // then
        assertThat(value).isEqualTo("world");
        assertThat(size).isEqualTo(2);

        redisTemplate.delete("listKey");
    }

    @Test
    void testHash() {
        // given
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String key = "hashKey";

        // when
        hashOperations.put(key, "hello", "world");

        // then
        Object value = hashOperations.get(key, "hello");
        assertThat(value).isEqualTo("world");

        Map<Object, Object> entries = hashOperations.entries(key);
        assertThat(entries.keySet()).containsExactly("hello");
        assertThat(entries.values()).containsExactly("world");

        Long size = hashOperations.size(key);
        assertThat(size).isEqualTo(entries.size());

        redisTemplate.delete("hashKey");
    }
}
