package com.github.controller;

import com.github.entity.Reading;
import com.github.service.ReadingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Api(description = "Endpoint to POST vehicle readings")
@RestController
public class ReadingsController {

    @Autowired
    ReadingsService readingsService;

    @RequestMapping(value = "/readings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "inserts vehicle readings into database")
    public Reading create(@RequestBody Reading reading) {
        Reading returnReading = readingsService.create(reading);
        readingsService.checkAlert(reading);
        return returnReading;
    }
}
