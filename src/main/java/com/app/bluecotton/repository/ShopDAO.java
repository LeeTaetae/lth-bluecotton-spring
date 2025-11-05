package com.app.bluecotton.repository;

import com.app.bluecotton.domain.dto.ProductListResponseDTO;
import com.app.bluecotton.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShopDAO {

    private final ShopMapper shopMapper;

    // 상품 전체 조회
    public List<ProductListResponseDTO> findAll() {
        return shopMapper.selectAll();
    }

}
