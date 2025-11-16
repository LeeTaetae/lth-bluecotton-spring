package com.app.bluecotton.mapper;

import com.app.bluecotton.domain.dto.DailyRevenue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RevenueMapper {

    public List<DailyRevenue> selectDailyRevenue();
}
