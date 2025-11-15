package com.app.bluecotton.domain.dto;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberResponseDTO {
    private Long id;
    private Long chatId;
    private Long memberId;
    private String memberName;
}
