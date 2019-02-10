package com.github.service;

import com.github.entity.Reading;

import org.springframework.stereotype.Service;

@Service
public interface ReadingsService {
    Reading create(Reading reading);
}
