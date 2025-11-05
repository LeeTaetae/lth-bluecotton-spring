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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop/*")
@RequiredArgsConstructor
@Slf4j
public class ShopApi {

    private final ShopService shopService;

    @GetMapping("")
    // 메인 페이지 상품 전체 조회
    public ResponseEntity<ApiResponseDTO> getProducts(){

        log.info("get 요청 들어옴");

        List<ProductListResponseDTO> products = shopService.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("상품 전체 조회 성공", products));
    }

}
