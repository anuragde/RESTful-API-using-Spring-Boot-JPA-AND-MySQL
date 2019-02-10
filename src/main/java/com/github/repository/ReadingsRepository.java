package com.github.repository;

import com.github.entity.Reading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingsRepository extends CrudRepository<Reading, String> {

}
