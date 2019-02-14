package com.github.service;

import com.github.entity.Alert;
import com.github.entity.Reading;
import com.github.entity.Vehicle;
import com.github.exception.BadRequestException;
import com.github.repository.AlertRepository;
import com.github.repository.ReadingsRepository;
import com.github.repository.VehicleRepository;
import org.hibernate.FlushMode;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ReadingsServiceImplementation implements ReadingsService {
    private final KieContainer kieContainer;
    @Autowired
    ReadingsRepository readingsRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    AlertRepository alertRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public ReadingsServiceImplementation(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @Transactional
    public Reading create(Reading reading) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByVin(reading.getVin());
        System.out.println(vehicleOptional.isPresent());
        if (!vehicleOptional.isPresent()) {

            throw new BadRequestException("Vehicle with VIN " + reading.getVin() + " doesn't exists.");


        }
        return readingsRepository.save(reading);
    }

    @Transactional
    public void checkAlert(Reading reading) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        Alert alert = new Alert();
        KieSession kieSession = kieContainer.newKieSession("rulesSession");
//        kieSession.setGlobal("alert", alert);
        kieSession.insert(alert);
        kieSession.insert(reading);
        kieSession.fireAllRules();
        kieSession.dispose();


        //alert.setUuid(uuid);

        if (alert.getPriority().equals("High") || alert.getPriority().equals("Low") || alert.getPriority().equals("Medium"))
            alert.setReading(reading);
            entityManager.persist(alert);
        entityManager.flush();
        entityManager.clear();
    }
}
