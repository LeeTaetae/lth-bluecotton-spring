package com.app.bluecotton.api.privateapi;

import com.app.bluecotton.domain.dto.ApiResponseDTO;
import com.app.bluecotton.domain.dto.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/members/*")
@RequiredArgsConstructor
@Slf4j
public class MemberAuthApi {
    @GetMapping ("me")
    public ResponseEntity<ApiResponseDTO> memberAuth(Authentication authentication){
        log.info("{}", authentication.getPrincipal());
        MemberResponseDTO currentMember = (MemberResponseDTO) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("최초 로그인", currentMember));
    }
}
