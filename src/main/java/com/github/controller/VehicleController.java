package com.github.controller;

import com.github.entity.Vehicle;
import com.github.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@Api(description = "Endpoint to manage vehicle details")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;
    @ApiOperation(value = "Endpoint to insert a list of values into Database")
    @RequestMapping(method = RequestMethod.PUT, value = "/vehicles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> create(@RequestBody List<Vehicle> vehiclesList) {
        return vehicleService.create(vehiclesList);
    }

    @ApiOperation(value = "Endpoint to update vehicle details by VIN")
    @RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle update(@PathVariable String vin, @RequestBody Vehicle vehicle) {
        return vehicleService.update(vin, vehicle);
    }

    @ApiOperation(value = "Endpoint to get vehicle details by VIN")
    @RequestMapping(method = RequestMethod.GET, value = "/vehicles/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle findOne(@PathVariable String vin) {
        return vehicleService.findOne(vin);
    }

    @ApiOperation(value = "Endpoint to get all vehicle details")
    @RequestMapping(method = RequestMethod.GET, value = "/vehicles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    @ApiOperation(value = "Endpoint to delete vehicle by VIN")
    @RequestMapping(method = RequestMethod.DELETE, value = "/vehicles/{vin}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void delete(@PathVariable String vin) {
        vehicleService.delete(vin);
    }
}
