package com.github.service;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsyncClient;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
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

import java.util.HashMap;
import java.util.Map;
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
    AlertRepository entityManager;

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
        Alert alert = new Alert();

        KieSession kieSession = kieContainer.newKieSession("rulesSession");
//        kieSession.setGlobal("alert", alert);
        kieSession.insert(entityManager);
        kieSession.insert(alert);
        kieSession.insert(reading);
        kieSession.fireAllRules();



        //alert.setUuid(uuid);

        if (alert.getPriority().equals("High") || alert.getPriority().equals("Low") || alert.getPriority().equals("Medium")){
            //send sms to registered mobile number
           //create a new SNS client and set endpoint
            AmazonSNS snsClient = AmazonSNSClient.builder().withRegion("us-east-1").build();
            String message = "High Alert triggered for Vehicle: " + reading.getVin() + " located currently at" + reading.getLatitude() + " , " + reading.getLongitude();
            String phoneNumber = "+13463070105";
            Optional<Vehicle> optionalVehicle = vehicleRepository.findByVin(reading.getVin());
            if(optionalVehicle.isPresent() && optionalVehicle.get().getRegisteredMobile() > 1000 && Long.toString(optionalVehicle.get().getRegisteredMobile()).length() == 10)
                phoneNumber = Long.toString(optionalVehicle.get().getRegisteredMobile());
            Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
            //<set SMS attributes>
            sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);

            smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                    .withStringValue("mySenderID") //The sender ID shown on the device.
                    .withDataType("String"));
            smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                    .withStringValue("Transactional") //Sets the type to promotional.
                    .withDataType("String"));


            alert.setReading(reading);

        }

//        entityManager.flush();
//        entityManager.clear();
        kieSession.dispose();
    }

    public static void sendSMSMessage(AmazonSNS snsClient, String message,
                                      String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {
        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes));
        System.out.println(result); // Prints the message ID.
    }
}
