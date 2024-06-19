package com.example.project.direction.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OutputDto {
    private String pharmacyName;
    private String pharmacyAddress;
    private String directionUrl; // 길안내 url
    private String roadViewUrl;  // 로드뷰 url
    private String distance;    // 고객 주소와 약국 주소의 거리

}
