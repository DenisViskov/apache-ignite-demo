package com.example.apacheignitedemo.cache;

import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class IgniteCacheTest {

    private static final String CACHE_NAME = "simple_demo_cache";

    @Autowired
    private IgniteClient igniteClient;

    @Test
    void shouldCreateCache() {
        var cacheNames = igniteClient.cacheNames();

        assert cacheNames.isEmpty();

        igniteClient.createCache(CACHE_NAME);
        cacheNames = igniteClient.cacheNames();

        assert !cacheNames.isEmpty();
        assert cacheNames.contains(CACHE_NAME);
    }

    @Test
    void shouldInteractWithCache() {
        ClientCache<Long, String> simpleDemoCache = igniteClient.createCache(CACHE_NAME);
        assert simpleDemoCache.size() == 0;

        simpleDemoCache.put(1L, "first");
        assert Objects.equals(simpleDemoCache.get(1L), "first");

        simpleDemoCache.put(1L, "second");
        assert Objects.equals(simpleDemoCache.get(1L), "second");
    }

    @Test
    void shouldInteractAsMap() {
        ClientCache<Integer, String> simpleDemoCache = igniteClient.createCache(CACHE_NAME);
        Map<Integer, String> intStringMap = IntStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toMap(it -> it, Object::toString));

        simpleDemoCache.putAll(intStringMap);
        assert !simpleDemoCache.replace(1, "2", "3");
        assert simpleDemoCache.get(1).equals("1");
        assert simpleDemoCache.replace(1, "1", "3");
        assert simpleDemoCache.get(1).equals("3");

        simpleDemoCache.put(11, "11");
        simpleDemoCache.removeAll(intStringMap.keySet());
        assert simpleDemoCache.size() == 1;
        assert "11".equals(simpleDemoCache.get(11));

        simpleDemoCache.removeAll();
        assert 0 == simpleDemoCache.size();
    }

    @AfterEach
    void destroyCache() {
        igniteClient.destroyCache(CACHE_NAME);
    }
}
