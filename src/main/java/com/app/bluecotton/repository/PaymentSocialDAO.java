package com.app.bluecotton.repository;

import com.app.bluecotton.domain.vo.shop.PaymentSocialVO;
import com.app.bluecotton.mapper.PaymentSocialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentSocialDAO {

    private final PaymentSocialMapper paymentSocialMapper;

    public void save(PaymentSocialVO paymentSocialVO) {
        paymentSocialMapper.insert(paymentSocialVO);
    }

    public List<PaymentSocialVO> findAll(Long paymentId) {
        return paymentSocialMapper.selectByPaymentId(paymentId);
    }

    public Optional<PaymentSocialVO> findById(Long id) {
        return paymentSocialMapper.selectById(id);
    }

    public List<PaymentSocialVO> findByPaymentId(Long paymentId) {
        return paymentSocialMapper.selectByPaymentId(paymentId);
    }

    public void update(PaymentSocialVO vo) {
        paymentSocialMapper.update(vo);
    }

    public void delete(Long id) {
        paymentSocialMapper.delete(id);
    }
}
