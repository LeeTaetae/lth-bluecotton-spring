package com.app.bluecotton.repository;

import com.app.bluecotton.domain.vo.shop.PaymentVO;
import com.app.bluecotton.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentDAO {

    private final PaymentMapper paymentMapper;

    public void save(PaymentVO paymentVO) {
        paymentMapper.insert(paymentVO);
    }

    public Optional<PaymentVO> findById(Long id) {
        return paymentMapper.selectById(id);
    }

    public List<PaymentVO> findByOrderId(Long orderId) {
        return paymentMapper.selectByOrderId(orderId);
    }

    public void updateStatus(PaymentVO paymentVO) {
        paymentMapper.updateStatus(paymentVO);
    }

    public void delete(Long id) {
        paymentMapper.delete(id);
    }
}
