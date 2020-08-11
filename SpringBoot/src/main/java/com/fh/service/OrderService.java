package com.fh.service;

import com.fh.common.ExceptionAll.CountException;
import com.fh.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Map createOrder(Integer addressId, Integer payType, String cartIds) throws CountException;

    Map createMoneyPhoto(Integer orderId) throws Exception;

    Integer queryPayStatus(Integer orderId) throws Exception;


    Map selectOrder();


    Order selectOrderById(Integer orderId);
}
