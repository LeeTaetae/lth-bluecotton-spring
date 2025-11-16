package com.app.bluecotton.domain.dto;


import com.app.bluecotton.domain.vo.shop.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyShopOrderDTO {

    private Long orderId;
    private Long productId;
    private String productName;
    private String productMainImageUrl;

    private Date orderCreateAt;

    private PaymentStatus paymentStatus;
    private String orderStatus;
}
