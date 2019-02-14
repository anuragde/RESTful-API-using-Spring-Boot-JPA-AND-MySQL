package com.github.repository;

import com.github.entity.Alert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AlertRepository extends CrudRepository<Alert, String> {
    List<Alert> findAllByReading_VinEquals(String vin);

    List<Alert> findAllByPriorityAndReading_VinAndReading_TimestampBefore(String priority, String vin, Instant timeStamp);

}
