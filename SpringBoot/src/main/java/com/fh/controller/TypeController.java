package com.fh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fh.model.Type;
import com.fh.common.jsonData;
import com.fh.service.TypeService;
import com.fh.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
@RequestMapping("TypeController")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("selectTypeAll")
    public jsonData selectTypeAll(){
        List<Type> typeList=typeService.selectTypeAll();
        return jsonData.getJsonSuccess(typeList);
    }

    @RequestMapping("selectTypeAll2")
    public jsonData selectTypeAll2(){
        Jedis jedis = RedisUtils.getJedis();
        String type = jedis.get("type");
        if (StringUtils.isEmpty(type)==true) {
            List<Type> typeList=typeService.selectTypeAll();
            String typeAll = JSONObject.toJSONString(typeList);
            jedis.set("type",typeAll);
        }
        RedisUtils.returnJedis(jedis);
        return jsonData.getJsonSuccess(jedis.get("type"));
    }

    //查询全部的类型
    @RequestMapping("selectType")
    public jsonData selectType(){
        Jedis jedis = RedisUtils.getJedis();
        String type = jedis.get("type");
        RedisUtils.returnJedis(jedis);
        return jsonData.getJsonSuccess(type);
    }

}
