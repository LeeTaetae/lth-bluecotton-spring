package com.app.bluecotton.mapper;

import com.app.bluecotton.domain.dto.ProductListResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {

    // 샵 메인 페이지 상품 전체 조회
    public List<ProductListResponseDTO> selectAll();

    // 샵 검색 상품 조회
    public ProductListResponseDTO selectByKeyword(String productName);

}
