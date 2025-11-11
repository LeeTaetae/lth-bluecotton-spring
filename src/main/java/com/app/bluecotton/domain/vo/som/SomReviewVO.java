package com.app.bluecotton.domain.vo.som;

import lombok.Data;

@Data
public class SomReviewVO {
    private Long id;
    private boolean somReviewIsChecked;
    public String getSomReviewIsCheckedYn() {
        return Boolean.TRUE.equals(somReviewIsChecked) ? "Y" : "N";
    }
    private String somReviewContent;
    private Long memberId;
    private Long somId;
}
