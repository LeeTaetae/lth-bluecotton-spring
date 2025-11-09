package com.app.bluecotton.api.publicapi;

import com.app.bluecotton.domain.dto.ApiResponseDTO;
import com.app.bluecotton.domain.dto.CartResponseDTO;
import com.app.bluecotton.domain.vo.shop.OrderVO;
import com.app.bluecotton.domain.vo.shop.PaymentStatus;
import com.app.bluecotton.domain.vo.shop.PaymentVO;
import com.app.bluecotton.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payment/*")
public class PaymentApi {

    private final PaymentService paymentService;

    @PostMapping("create")
    public ResponseEntity<ApiResponseDTO> create(@RequestBody PaymentVO paymentVO) {
        paymentService.create(paymentVO);
        log.info("✅ Payment created: {}", paymentVO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of("결제 생성 성공"));
    }

    @GetMapping("option")
    public ResponseEntity<ApiResponseDTO<Optional<PaymentVO>>> getOne(@RequestParam Long id) {
        Optional<PaymentVO> pay = paymentService.findById(id);
        return ResponseEntity.ok(ApiResponseDTO.of("결제 확인 완료", pay));
    }

    @GetMapping("list")
    public ResponseEntity<ApiResponseDTO<List<PaymentVO>>> getByOrderId(@RequestParam Long orderId) {
        List<PaymentVO> pays = paymentService.findByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("결제 리스트 확인 완료", pays));
    }

    @PutMapping("status")
    public ResponseEntity<ApiResponseDTO> changeStatus(
            @RequestParam Long id,
            @RequestParam("status") PaymentStatus status
    ) {
        paymentService.changeStatus(id, status);
        log.info("🔄 Payment status changed: id={}, status={}", id, status);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("상태 변경 완료: " + status));
    }

    @PutMapping("fail")
    public ResponseEntity<ApiResponseDTO> markFailed(@RequestParam Long id) {
        paymentService.markFailed(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("결제 실패 처리"));
    }

    @PutMapping("cancel")
    public ResponseEntity<ApiResponseDTO> markCanceled(@RequestParam Long id) {
        paymentService.markCanceled(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("결제 취소 처리"));
    }


    @DeleteMapping("delete")
    public ResponseEntity<ApiResponseDTO> delete(@RequestParam Long id) {
        paymentService.delete(id);
        log.info("🗑️ Payment deleted: id={}", id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("삭제 완료"));
    }

}
