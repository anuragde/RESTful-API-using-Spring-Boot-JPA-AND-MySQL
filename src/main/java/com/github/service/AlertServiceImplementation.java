package com.github.service;

import com.github.entity.Alert;
import com.github.entity.Reading;
import com.github.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AlertServiceImplementation implements AlertService {

    @Autowired
    AlertRepository alertRepository;

    public List<Alert> findAllByVehicleReading_Vin(Reading reading) {
        return alertRepository.findAllByReading_VinEquals(reading.getVin());
    }

    public List<Alert> findAllByPriorityAndReading_VinAndReading_Timestamp(Reading reading) {
        return alertRepository.findAllByPriorityAndReading_VinAndReading_TimestampBefore("High", reading.getVin(), Instant.now().minus(24, ChronoUnit.HOURS));
    }



}
