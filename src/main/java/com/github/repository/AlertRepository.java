package com.github.repository;

import com.github.entity.Alert;
import com.github.entity.Reading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends CrudRepository<Alert, String> {
    List<Alert> findAllByVinEquals(String vin);

}
