package com.app.bluecotton.domain.vo.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    private Long id;
    private String productName;
    private Integer productPrice;
    private Integer productStock;
    private ProductCategory productCategory;
    private ProductType productType;
    private ProductPurchaseType productPurchaseType;
    private String productMainDescription;
    private String productSubDescription ;
    private String productWeight;
    private String productMaterial;
}
