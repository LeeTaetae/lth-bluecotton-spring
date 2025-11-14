package com.app.bluecotton.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductReviewRecommendDTO {

    private Long id;
    private Integer productReviewRecommendCount;
    private Integer isRecommended;

}
