package com.github.service;

import com.github.entity.Reading;
import com.github.entity.Vehicle;
import com.github.exception.BadRequestException;
import com.github.repository.ReadingsRepository;
import com.github.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ReadingsServiceImplementation implements ReadingsService {

    @Autowired
    ReadingsRepository readingsRepository;
    @Autowired
    VehicleRepository vehicleRepository;

    @Transactional()
    public Reading create(Reading reading) {
        Optional<Vehicle> readingOptional = vehicleRepository.findById(reading.getVin());
        if (!readingOptional.isPresent()) {
            throw new BadRequestException("Vehicle with VIN " + reading.getVin() + " doesn't exists.");
        }
        return readingsRepository.save(reading);
    }
}
