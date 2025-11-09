package com.app.bluecotton.mapper;

import com.app.bluecotton.domain.vo.shop.PaymentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PaymentMapper {

    public void insert(PaymentVO paymentVO);


    public Optional<PaymentVO> selectById(long id);

    public List<PaymentVO> selectByOrderId(Long orderId);

    public void updateStatus(PaymentVO paymentVO);

    public void delete(Long id);
}

