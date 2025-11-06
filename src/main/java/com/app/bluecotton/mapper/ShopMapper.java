package com.app.bluecotton.mapper;

import com.app.bluecotton.domain.dto.ProductListResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopMapper {

    // 상품 조건 조회
    public List<ProductListResponseDTO> selectProductsByFilter(Map<String,Object> params);

}
