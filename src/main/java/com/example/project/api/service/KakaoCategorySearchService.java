package com.example.project.api.service;

import com.example.project.api.dto.KakaoApiResponseDto;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoCategorySearchService {

    private final KakaoUriBuilderService kakaoUriBuilderService;

    private final RestTemplate restTemplate;

    private static final String PHARMACY_CATEGORY = "PM9";  // 약국 카테고리

    @Value("${KAKAO_REST_API_KEY}")
    private String kakaoRestApiKey;

    public KakaoApiResponseDto requestPharmacyCategorySearch(double latitude, double longitude, double radius) {

        URI uri = kakaoUriBuilderService.buildUriByCategorySearch(latitude, longitude, radius, PHARMACY_CATEGORY);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK "+ kakaoRestApiKey);
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class).getBody();
    }
}
