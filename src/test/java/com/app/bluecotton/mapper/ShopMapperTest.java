package com.app.bluecotton.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ShopMapperTest {

    @Autowired
    ShopMapper shopMapper;

    @Test
    void selectProductsByFilter() {
        Map<String,Object> params = new HashMap<>();
        params.put("clothing", "CLOTHING");
        params.put("order", "신상품순");
        log.info("{}",shopMapper.selectProductsByFilter(params));
    }
}