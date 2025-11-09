package com.app.bluecotton.service;


import com.app.bluecotton.domain.dto.OrderCartDTO;
import com.app.bluecotton.domain.dto.OrderDTO;
import com.app.bluecotton.domain.vo.shop.OrderVO;
import com.app.bluecotton.repository.CartDAO;
import com.app.bluecotton.repository.OrderDAO;
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
public class OrderServiceImpl implements OrderService {


    private final OrderDAO orderDAO;
    private final CartDAO cartDAO;

    @Override
    public void addOrder(OrderDTO orderDTO) {
        orderDAO.addOrder(orderDTO);
    }

    @Override
    public void addOrderCart(OrderCartDTO orderCartDTO) {
        orderDAO.addOrderCart(orderCartDTO);
    }

    @Override
    public List<OrderVO> selectAllOrders(Long memberId) {
        return orderDAO.selectAllOrders(memberId);
    }

    @Override
    public Optional<OrderVO> selectOrderById(Long id, Long memberId) {
        return orderDAO.selectOrderById(id, memberId);
    }

    @Override
    public void updateOrder(OrderVO orderVO) {
        orderDAO.updateOrder(orderVO);
    }

    @Override
    public void deleteOrder(Long id, Long memberId) {
        orderDAO.deleteOrder(id, memberId);
    }

    @Override
    public void clearCartAfterOrder(Long memberId) {
        // 1) 주문 테이블에서 카트 FK 끊기 (ORDER_STATUS='Y' 대상)
        orderDAO.detachOrderFromCart(memberId);
        // 2) 해당 회원 장바구니 전체 삭제
        cartDAO.deleteAllByMember(memberId);
    }

}
