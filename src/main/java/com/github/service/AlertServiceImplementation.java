package com.github.service;

import com.github.entity.Alert;
import com.github.entity.Reading;
import com.github.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertServiceImplementation implements AlertService {

    @Autowired
    AlertRepository alertRepository;

    public List<Alert> findAllByVehicleReading_Vin(Reading reading) {
        return alertRepository.findAllByVinEquals(reading.getVin());
    }


}
