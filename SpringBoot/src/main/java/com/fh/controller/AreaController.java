package com.fh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fh.model.Area;
import com.fh.common.jsonData;
import com.fh.service.AreaService;
import com.fh.util.RedisUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
@RequestMapping("AreaController")
@Api(description = "这是查询地区接口")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @RequestMapping("selectAreaAll")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "参数",name = "name",dataType = "string",required = true),
            @ApiImplicitParam(paramType = "参数",name = "id",dataType = "Integer",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数没有填好",response = String.class),
            @ApiResponse(code = 404,message = "路径不对",response = String.class),
    })
    public jsonData selectAreaAll(String name,Integer id){
        List<Area> areaList=areaService.selectAreaAll();
        return jsonData.getJsonSuccess(areaList);
    }

    @RequestMapping("selectAreaAll2")
    public jsonData selectAreaAll2(){
        Jedis jedis = RedisUtils.getJedis();
        String area = jedis.get("area");
        if (StringUtils.isEmpty(area)==true) {
            List<Area> areaList=areaService.selectAreaAll();
            String sale = JSONObject.toJSONString(areaList);
            jedis.set("area",sale);
        }
        RedisUtils.returnJedis(jedis);
        return jsonData.getJsonSuccess(jedis.get("area"));
    }


}
