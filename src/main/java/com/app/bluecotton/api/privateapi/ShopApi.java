package com.app.bluecotton.api.privateapi;

import com.app.bluecotton.domain.dto.ApiResponseDTO;
import com.app.bluecotton.domain.dto.ProductListResponseDTO;
import com.app.bluecotton.domain.vo.post.PostVO;
import com.app.bluecotton.domain.vo.shop.ProductVO;
import com.app.bluecotton.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop/*")
@RequiredArgsConstructor
@Slf4j
public class ShopApi {

    private final ShopService shopService;

    // 상품 조건 조회
    @PostMapping("")
    public ResponseEntity<ApiResponseDTO> getProductsByFilter(@RequestBody Map<String, Object> params) {
        log.info("상품 조건 조회 요청 들어옴: {}", params);
        List<ProductListResponseDTO> products = shopService.getProductByFilter(params);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("상품 조건 조회 성공", products));
    }

}
