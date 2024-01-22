package com.example.apacheignitedemo.cache;

import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
class IgniteCacheTest {

    @Autowired
    private IgniteClient igniteClient;

    @Test
    void shouldCreateCache() {
        var cacheNames = igniteClient.cacheNames();
        var cacheName = "simple_demo_cache";

        assert cacheNames.isEmpty();

        igniteClient.createCache(cacheName);
        cacheNames = igniteClient.cacheNames();

        assert !cacheNames.isEmpty();
        assert cacheNames.contains(cacheName);
        igniteClient.destroyCache(cacheName);
    }

    @Test
    void shouldInteractWithCache() {
        var cacheName = "simple_demo_cache";

        ClientCache<Long, String> simpleDemoCache = igniteClient.createCache(cacheName);
        assert simpleDemoCache.size() == 0;

        simpleDemoCache.put(1L, "first");
        assert Objects.equals(simpleDemoCache.get(1L), "first");

        simpleDemoCache.put(1L, "second");
        assert Objects.equals(simpleDemoCache.get(1L), "second");

        igniteClient.destroyCache(cacheName);
    }
}
