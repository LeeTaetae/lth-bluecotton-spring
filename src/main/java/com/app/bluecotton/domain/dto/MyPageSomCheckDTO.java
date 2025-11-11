package com.app.bluecotton.domain.dto;

import com.app.bluecotton.domain.vo.som.SomCheckImageVO;
import com.app.bluecotton.domain.vo.som.SomCheckVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPageSomCheckDTO {
//            ID, SOM_CHECK_IS_CHECKED, SOM_CHECK_CONTENT, MEMBER_ID, SOM_ID
//             ID, SOM_CHECK_IMAGE_PATH, SOM_CHECK_IMAGE_NAME, SOM_CHECK_ID
    private Long somCheckId;
    private Boolean somCheckIsChecked;
    public String getSomCheckIsCheckedYn() {
        return Boolean.TRUE.equals(somCheckIsChecked) ? "Y" : "N";
    }
    private String somCheckContent;
    private Long somCheckImageId;
    private List<String> somCheckImagePath;
    private List<String> somCheckImageName;
    private Long memberId;
    private Long somId;

    public MyPageSomCheckDTO(SomCheckVO somCheckVO, SomCheckImageVO somCheckImageVO) {
        this.somCheckId = somCheckVO.getId();
        this.somCheckIsChecked = somCheckVO.isSomCheckIsChecked();
        this.somCheckContent = somCheckVO.getSomCheckContent();
        this.somCheckImageId = somCheckImageVO.getId();
        this.somCheckImagePath = somCheckImageVO.getSomCheckImagePath();
        this.somCheckImageName = somCheckImageVO.getSomCheckImageName();
        this.memberId = somCheckVO.getMemberId();
        this.somId = somCheckVO.getSomId();
    }
}
