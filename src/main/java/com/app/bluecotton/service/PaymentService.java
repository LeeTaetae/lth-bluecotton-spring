package com.app.bluecotton.service;

import com.app.bluecotton.domain.vo.shop.PaymentStatus;
import com.app.bluecotton.domain.vo.shop.PaymentVO;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    public void create(PaymentVO paymentVO);

    public Optional<PaymentVO> findById(Long id);

    public List<PaymentVO> findByOrderId(Long orderId);

    public void changeStatus(Long id, PaymentStatus nextStatus);

    public void markCompleted(Long id);

    public void markFailed(Long id);

    public void markCanceled(Long id);

    public void delete(Long id);

}
