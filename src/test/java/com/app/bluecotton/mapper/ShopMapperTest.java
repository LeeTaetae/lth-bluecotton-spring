package com.app.bluecotton.mapper;

import com.app.bluecotton.domain.dto.ProductListResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ShopMapperTest {

    @Autowired
    private ShopMapper shopMapper;

    @Test
    void selectAllTest() {
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        List<ProductListResponseDTO> productListResponseDTOS = shopMapper.selectAll();
        log.info("{}",productListResponseDTOS);
    }
}