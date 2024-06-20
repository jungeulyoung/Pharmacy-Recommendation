package com.example.project.pharmacy.service;

import com.example.project.pharmacy.cache.PharmacyRedisTemplateService;
import com.example.project.pharmacy.dto.PharmacyDto;
import com.example.project.pharmacy.entity.Pharmacy;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PharmacySearchService {

    private final PharmacyRepositoryService pharmacyRepositoryService;
    private final PharmacyRedisTemplateService pharmacyRedisTemplateService;

    public List<PharmacyDto> searchPharmacyDtoList() {

        //redis
        List<PharmacyDto> pharmacyDtoList = pharmacyRedisTemplateService.findAll();
        if(!pharmacyDtoList.isEmpty()){
            log.info("redis findAll success!");
            return pharmacyDtoList;
        }


        //database
        return pharmacyRepositoryService.findAll()
                .stream()
                .map(this::convertToPharmacyDto)
                .collect(Collectors.toList());
    }


    private PharmacyDto convertToPharmacyDto(Pharmacy pharmacy) {

        return PharmacyDto.builder()
                .id(pharmacy.getId())
                .pharmacyAddress(pharmacy.getPharmacyAddress())
                .pharmacyName(pharmacy.getPharmacyName())
                .latitude(pharmacy.getLatitude())
                .longitude(pharmacy.getLongitude())
                .build();
    }

}
