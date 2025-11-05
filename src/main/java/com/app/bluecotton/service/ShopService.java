package com.app.bluecotton.service;

import com.app.bluecotton.domain.dto.ProductListResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShopService {

    // 샵 메인 페이지 전체 조회
    public List<ProductListResponseDTO> getProducts();

}
