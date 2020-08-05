package com.fh.service;

import java.util.List;

public interface CartService {
    Integer addShopToCart(Integer proId, Integer count);

    List selectCartAll();

    void updateCartSum(Integer id);

    void updateCartSumAdd(Integer id);

    void deleteCart(Integer id);
}
