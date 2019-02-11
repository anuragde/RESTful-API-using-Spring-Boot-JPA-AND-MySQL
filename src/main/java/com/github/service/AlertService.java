package com.github.service;

import com.github.entity.Alert;
import com.github.entity.Reading;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlertService {

    List<Alert> findAllByVehicleReading_Vin(Reading reading);

}
