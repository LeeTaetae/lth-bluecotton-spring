package com.app.bluecotton.service;

import com.app.bluecotton.domain.dto.ProductListResponseDTO;
import com.app.bluecotton.repository.ShopDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopDAO shopDAO;

    @Override
    public List<ProductListResponseDTO> getProducts() {
        return shopDAO.findAll();
    }
}
