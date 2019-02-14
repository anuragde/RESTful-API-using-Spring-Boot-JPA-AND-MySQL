package com.github.service;

import com.github.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {

    Vehicle findOne(String id);

    List findAll();

    List<Vehicle> create(List<Vehicle> vehiclesList);

    Vehicle update(String vin, Vehicle vehicle);

    void delete(String id);
}
