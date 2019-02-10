package com.github.service;

import com.github.entity.Vehicle;
import com.github.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class VehicleServiceImplementation implements VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    @Transactional()
    public Vehicle findOne(String vin) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vin);
        if (!vehicle.isPresent()) {
            throw new ResourceNotFoundException("Vehicle with vin " + vin + " doesn't exist.");
        }
        return vehicle.get();
    }

    @Transactional()
    public List<Vehicle> findAll() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Transactional
    public List<Vehicle> create(List<Vehicle> vehiclesList) {
        return (List<Vehicle>) vehicleRepository.saveAll(vehiclesList);
    }

    @Transactional()
    public Vehicle update(String vin, Vehicle vehicle) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByMake(vehicle.getVin());
        if (!vehicleOptional.isPresent()) {
            throw new ResourceNotFoundException("Vehicle with make " + vehicle.getVin() + " doesn't exist.");
        }
        return vehicleRepository.save(vehicle);
    }


    @Transactional()
    public void delete(String vin) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vin);
        if (!vehicleOptional.isPresent()) {
            throw new ResourceNotFoundException("Vehicle with VIN " + vin + " doesn't exist.");
        }
        vehicleRepository.delete(vehicleOptional.get());
    }


}
