package com.app.bluecotton.repository;

import com.app.bluecotton.domain.dto.ProductListResponseDTO;
import com.app.bluecotton.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ShopDAO {

    private final ShopMapper shopMapper;

    public List<ProductListResponseDTO> findProductsByFilter(Map<String,Object> params){
        return shopMapper.selectProductsByFilter(params);
    }

}
