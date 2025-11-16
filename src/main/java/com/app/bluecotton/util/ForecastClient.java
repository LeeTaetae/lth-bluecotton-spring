package com.app.bluecotton.util;

import com.app.bluecotton.domain.dto.DailyRevenue;
import com.app.bluecotton.domain.dto.ForecastRequest;
import com.app.bluecotton.domain.dto.RevenueForecastPoint;
import com.app.bluecotton.domain.dto.RevenueForecastResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ForecastClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;   // ✅ 스프링이 자동으로 주입해 줌

    @Value("${forecast.url:http://localhost:8001/forecast}")
    private String forecastUrl;

    /**
     * FastAPI /forecast 호출
     */
    public RevenueForecastResponse requestForecast(List<DailyRevenue> history, int horizon) {

        // 1) 요청 DTO 만들기
        ForecastRequest request = new ForecastRequest();
        request.setHorizon(horizon);

        List<ForecastRequest.Point> series = history.stream()
                .map(d -> {
                    ForecastRequest.Point p = new ForecastRequest.Point();
                    p.setDate(d.getPayDate());          // "yyyy-MM-dd"
                    p.setRevenue(d.getRevenue());    // Long
                    return p;
                })
                .collect(Collectors.toList());

        request.setSeries(series);

        // 2) JSON 직렬화 + 로그
        String jsonBody;
        try {
            jsonBody = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            log.error("[ForecastClient] 요청 직렬화 실패", e);
            return new RevenueForecastResponse(); // data == null
        }

        log.info("[ForecastClient] 예측 요청 URL={}, horizon={}, series.size={}, body={}",
                forecastUrl, horizon, series.size(), jsonBody);

        // 3) 헤더 & HttpEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // ✅ String(JSON) 을 body로 직접 넣는다.
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        try {
            ResponseEntity<RevenueForecastResponse> response =
                    restTemplate.exchange(
                            forecastUrl,
                            HttpMethod.POST,
                            entity,
                            RevenueForecastResponse.class
                    );

            RevenueForecastResponse body = response.getBody();
            if (body == null) {
                log.warn("[ForecastClient] 예측 응답 body == null");
                return new RevenueForecastResponse();
            }

            List<RevenueForecastPoint> data = body.getData();
            log.info("[ForecastClient] 예측 응답 수신: {}개", data == null ? 0 : data.size());

            return body;

        } catch (Exception e) {
            log.error("[ForecastClient] 예측 요청 중 예외 발생", e);
            return new RevenueForecastResponse();
        }
    }
}
