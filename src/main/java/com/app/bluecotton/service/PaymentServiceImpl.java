package com.app.bluecotton.service;

import com.app.bluecotton.domain.vo.shop.PaymentStatus;
import com.app.bluecotton.domain.vo.shop.PaymentVO;
import com.app.bluecotton.repository.PaymentDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDAO paymentDAO;

    @Override
    public void create(PaymentVO paymentVO) {
        if (paymentVO.getPaymentStatus() == null) {
            paymentVO.setPaymentStatus(PaymentStatus.PENDING);
        }
        paymentDAO.save(paymentVO);
    }

    @Override
    public Optional<PaymentVO> findById(Long id) {
        return paymentDAO.findById(id);
    }

    @Override
    public List<PaymentVO> findByOrderId(Long orderId) {
        return paymentDAO.findByOrderId(orderId);
    }

    @Override
    public void changeStatus(Long id, PaymentStatus nextStatus) {
        PaymentVO vo = paymentDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("결제 내역이 없습니다. id=" + id));
        vo.setPaymentStatus(nextStatus);
        paymentDAO.updateStatus(vo);
    }

    @Override
    public void markCompleted(Long id) {
        changeStatus(id, PaymentStatus.COMPLETED);
    }

    @Override
    public void markFailed(Long id) {
        changeStatus(id, PaymentStatus.FAILED);
    }

    @Override
    public void markCanceled(Long id) {
        changeStatus(id, PaymentStatus.CANCELLED);
    }

    @Override
    public void delete(Long id) {
        paymentDAO.delete(id);
    }
}
