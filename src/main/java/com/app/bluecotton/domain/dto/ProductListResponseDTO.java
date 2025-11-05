package com.app.bluecotton.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponseDTO {

    private Long id;
    private String productName;
    private String productType;
    private String productPrice;
    private String productPurchaseType;
    private Double avgRating;
    private Integer reviewCount;
    private Integer likeCount;
    private String productImagePath;
    private String productImageName;

}
