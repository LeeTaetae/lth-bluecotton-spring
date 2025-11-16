package com.app.bluecotton.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDashboardResponse {
    private List<DailyRevenue> history;
    private List<RevenueForecastPoint> forecast;
}
