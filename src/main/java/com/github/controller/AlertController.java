package com.github.controller;

import com.github.entity.Alert;

import com.github.entity.Reading;
import com.github.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
public class AlertController {
    @Autowired
    AlertService alertService;

    @RequestMapping(value = "/getAllAlerts/{vin}", method = RequestMethod.GET)
    public List<Alert> getAllAlerts(@PathVariable String vin) {
        Reading reading = new Reading();
        reading.setVin(vin);
        return alertService.findAllByVehicleReading_Vin(reading);
    }


    @RequestMapping(value = "/getRecentHighAlerts/{vin}", method = RequestMethod.GET)
    public List<Alert> getRecentHighAlerts(@PathVariable String vin) {
        Reading reading = new Reading();
        reading.setVin(vin);
        return alertService.findAllByPriorityAndReading_VinAndReading_Timestamp(reading);
    }

}
