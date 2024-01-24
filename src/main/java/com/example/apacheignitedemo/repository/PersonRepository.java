package com.example.apacheignitedemo.repository;

import com.example.apacheignitedemo.model.PersonEntity;
import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RepositoryConfig(
        cacheName = "person_cache",
        igniteInstance = "igniteClient",
        autoCreateCache = true
)
public interface PersonRepository extends IgniteRepository<PersonEntity, Long> {

    List<PersonEntity> findByName(String name);

    @Query("SELECT * FROM Person WHERE id > ?")
    List<PersonEntity> selectPersonsWithIdGreaterThan(Long id, Pageable pageable);
}
