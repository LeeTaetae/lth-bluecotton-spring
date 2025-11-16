package com.app.bluecotton.service;

import com.app.bluecotton.domain.dto.DailyRevenue;
import com.app.bluecotton.domain.dto.RevenueForecastPoint;
import com.app.bluecotton.domain.dto.RevenueForecastResponse;
import com.app.bluecotton.mapper.RevenueMapper;
import com.app.bluecotton.util.ForecastClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // 로깅을 위한 Lombok Slf4j 추가
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j // 로깅 어노테이션 추가
@Service
@RequiredArgsConstructor
public class RevenueService {

    private final RevenueMapper revenueMapper;
    private final ForecastClient forecastClient;

    // 1) DB에서 일 매출 조회
    public List<DailyRevenue> getDailyRevenueHistory() {
        // 이 부분에서 발생하는 DB 예외는 Global Exception Handler에서 처리해야 합니다.
        return revenueMapper.selectDailyRevenue();
    }

    // 2) 대시보드용: history + forecast 같이 묶어서 반환
    public Map<String, Object> getRevenueDashboard(int horizon) {

        List<DailyRevenue> history = getDailyRevenueHistory();
        List<RevenueForecastPoint> forecast = Collections.emptyList();

        // 1. 히스토리가 비어있지 않고,
        // 2. 외부 예측 클라이언트 호출 시도 시 예외가 발생하지 않도록 try-catch로 감싸줍니다.
        if (!history.isEmpty()) {
            RevenueForecastResponse forecastResponse = null;

            try {
                // 외부 API 호출
                forecastResponse = forecastClient.requestForecast(history, horizon);

                // 3. 응답 객체 자체와 객체 내부의 데이터 모두 널 체크합니다.
                if (forecastResponse != null && forecastResponse.getData() != null) {
                    forecast = forecastResponse.getData();
                } else {
                    log.warn("Forecast Client returned null response or null data.");
                }

            } catch (Exception e) {
                // 외부 API 호출 중 통신 오류, 타임아웃, 예외 발생 시 빈 리스트로 처리하고 에러를 로깅합니다.
                log.error("Failed to request revenue forecast from external client with horizon: {}", horizon, e);
                // forecast는 이미 Collections.emptyList()로 초기화되어 있으므로 별도 할당 불필요
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("history", history);
        result.put("forecast", forecast);

        return result;
    }
}