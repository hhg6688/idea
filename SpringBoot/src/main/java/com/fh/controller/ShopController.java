package com.fh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fh.model.Shop;
import com.fh.common.jsonData;
import com.fh.service.ShopService;
import com.fh.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
@RequestMapping("ShopController")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @RequestMapping("selectShopAll")
    public jsonData selectShopAll(){
        List<Shop> shopList= shopService.selectShop();
        return jsonData.getJsonSuccess(shopList);
    }

    @RequestMapping("selectShopAll2")
    public jsonData selectShopAll2(){
        Jedis jedis = RedisUtils.getJedis();
        String Shop = jedis.get("shop");
        if (StringUtils.isEmpty(Shop)==true) {
            List<Shop> shopList= shopService.selectShop();
            String sale = JSONObject.toJSONString(shopList);
            jedis.set("shop",sale);
        }
        RedisUtils.returnJedis(jedis);
        return jsonData.getJsonSuccess(jedis.get("shop"));
    }

    @RequestMapping("selectShopSale")
    public jsonData selectShopSale(){
        List<Shop> shopSaleList=shopService.selectShopSale();
        return jsonData.getJsonSuccess(shopSaleList);
    }

    @RequestMapping("queryShopBySale")
    public jsonData queryShopBySale(){
        Jedis jedis = RedisUtils.getJedis();
        String saleShop = jedis.get("saleShop");
        if (StringUtils.isEmpty(saleShop)==true) {
            List<Shop> shopSaleList=shopService.selectShopSale();
            String sale = JSONObject.toJSONString(shopSaleList);
            jedis.set("saleShop",sale);
        }
        RedisUtils.returnJedis(jedis);
        return jsonData.getJsonSuccess(jedis.get("saleShop"));
    }

    //根据类型id查询对应的商品
    @RequestMapping("selectShopById")
    public jsonData selectShopById(String typeId){
    List<Shop> listShop= shopService.selectShopByTypeId(typeId);
        return jsonData.getJsonSuccess(listShop);
    }
}
