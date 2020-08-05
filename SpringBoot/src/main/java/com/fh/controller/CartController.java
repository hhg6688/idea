package com.fh.controller;

import com.fh.common.jsonData;
import com.fh.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("CartController")
public class CartController {
    @Autowired
    private CartService cartService;


    @RequestMapping("addCart")
    public jsonData addCart(@RequestParam("typeId")Integer proId, Integer count){
        Integer countType=cartService.addShopToCart(proId,count);
        return jsonData.getJsonSuccess(countType);
    }

    @RequestMapping("selectCartAllData")
    public jsonData selectCartAllData(){
        List shopCartList=cartService.selectCartAll();
        return jsonData.getJsonSuccess(shopCartList);
    }

    @RequestMapping("jianCartSum")
    public jsonData updateCart(Integer id){
        cartService.updateCartSum(id);
        return jsonData.getJsonSuccess("減少成功");
    }

    @RequestMapping("jiaCartSum")
    public jsonData updateCartSum(Integer id){
        cartService.updateCartSumAdd(id);
        return jsonData.getJsonSuccess("增加成功");
    }


    //删除redis中的key
    @RequestMapping("deleteCart")
    public jsonData deleteCart(Integer id){
        cartService.deleteCart(id);
        return jsonData.getJsonSuccess("删除购物车成功");
    }
}
