package com.app.bluecotton.mapper;

import com.app.bluecotton.domain.vo.shop.PaymentStatus;
import com.app.bluecotton.domain.vo.shop.PaymentVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class PaymentMapperTest {

    @Autowired
    private PaymentMapper paymentMapper;

    @Test
    public void insertTest() {
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setPaymentPrice(25000);
        paymentVO.setPaymentType("CASH");
        paymentVO.setPaymentStatus(PaymentStatus.COMPLETED);
        paymentVO.setOrderId(7L);

        paymentMapper.insert(paymentVO);
        log.info("✅ INSERT 완료 - 생성된 ID: {}", paymentVO.getId());
    }

    @Test
    public void  selectListByOrderIdTest() {
        Long orderId = 1L;
        paymentMapper.selectByOrderId(orderId);
    }

    @Test
    public void selectByIdTest() {
        Long memberId = 1L; // 존재하는 결제 ID
        Optional<PaymentVO> result = paymentMapper.selectById(memberId);

        if (result.isPresent()) {
            log.info("✅ 결제 단건 조회 성공: {}", result.get());
        } else {
            log.warn("⚠️ 결제 ID={} 존재하지 않음", memberId);
        }
    }


    @Test
    public void updateStatusTest() {
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setId(1L); // 수정할 결제 ID
        paymentVO.setPaymentStatus(PaymentStatus.CANCELLED);

        paymentMapper.updateStatus(paymentVO);
        log.info("✅ 결제 상태 업데이트 완료: {}", paymentVO);
    }

    @Test
    public void deleteTest() {
        Long deleteId = 1L; // 삭제할 결제 ID
        paymentMapper.delete(deleteId);
        log.info("🗑️ 결제 ID={} 삭제 완료", deleteId);
    }
}
