package com.app.bluecotton.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class PortOneClient {

    private String impKey;

    private String impSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken() {
        String url = "https://api.iamport.kr/users/getToken";

        Map<String, Object> map = new HashMap<>();
        map.put("imp_key", impKey);
        map.put("imp_secret", impSecret);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, map, Map.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("PortOne access token 발급 실패: " + response);
        }

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody().get("response");
        String accessToken = (String) responseBody.get("access_token");
        log.info("✅ PortOne access token 발급 성공: {}", accessToken);

        return accessToken;

    }

    public Map<String, Object> getPaymentByImpUid(String accessToken, String impUid) {
            String url = "https://api.iamport.kr/payments/" + impUid;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", accessToken);

            HttpEntity<Void> entity = new HttpEntity<>(headers);
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("결제내역 조회 실패: " + response);
            }

            Map<String, Object> payment = (Map<String, Object>) response.getBody().get("response");
            log.info("✅ PortOne 결제내역 조회 성공: imp_uid={}, status={}", impUid, payment.get("status"));

            return payment;

    }

    public void preparePayment(String accessToken, String merchantUid, long amount) {
        String url = "https://api.iamport.kr/payments/prepare";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("merchant_uid", merchantUid);
        body.put("amount", amount);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("결제 사전등록 실패: " + response);
        }

        log.info("✅ PortOne 결제금액 사전등록 완료: merchant_uid={}, amount={}", merchantUid, amount);
    }
}
