package com.fh.controller;

import com.fh.common.ExceptionAll.CountException;
import com.fh.model.Order;
import com.fh.service.OrderService;
import com.fh.common.jsonData;
import com.fh.util.AliPay;
import com.fh.util.MailUtil;
import com.fh.util.RedisUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("OrderController")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("createNumber")
    public jsonData createNumber(Integer addressId,Integer payType,String cartIds,String flag) throws CountException {
        //判断redis是否存在key
        boolean exists = RedisUser.exists(flag);
        //二次请求
        if(exists==true){
            return jsonData.getJsonError(300,"请求处理中");
        }else{
            RedisUser.set(flag,"",10);
        }
       Map map= orderService.createOrder(addressId,payType,cartIds);
        return jsonData.getJsonSuccess(map);
    }


    //创建二维码
    @RequestMapping("createMoneyPhoto")
    public jsonData createMoneyPhoto(Integer orderId) throws Exception {
        Map moneyPhoto=orderService.createMoneyPhoto(orderId);
        return jsonData.getJsonSuccess(moneyPhoto);
    }

    //创建支付宝二维码
    @RequestMapping("initZhiFuBaoPohto")
    public jsonData initZhiFuBaoPohto(Integer orderId) throws Exception {
        Order order=orderService.selectOrderById(orderId);
        String aliPay = AliPay.aliPay(order);
        return jsonData.getJsonSuccess(aliPay);
    }

    @RequestMapping("queryPayStatus")
    public jsonData queryPayStatus(Integer orderId) throws Exception {
        Integer status=orderService.queryPayStatus(orderId);
        return jsonData.getJsonSuccess(status);
    }

    //查询订单
    @RequestMapping("queryOrderData")
    public jsonData selectOrder(){
       Map map = orderService.selectOrder();
        return jsonData.getJsonSuccess(map);
    }




}
