package com.fh.controller;

import com.fh.util.JWT;
import com.fh.util.RedisUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fh.common.jsonData;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("LoginController")
public class LoginController {
    @RequestMapping("getCode")
    public jsonData getCode(String PhoneNumbers){
        String code="123456";
        RedisUser.set(PhoneNumbers+"_hhg",code,60*3);
        return jsonData.getJsonSuccess("短信发送成功");
    }

    @RequestMapping("loginUser")
    public jsonData loginUser(String iphoneNum, String code,Integer isLogin){
        Map logMap=new HashMap();
        //获取存在redis的code码
        String redisCode = RedisUser.get(iphoneNum + "_hhg");
        if(redisCode != null){
            if (redisCode.equals(code)){
                Map user=new HashMap();
                user.put("iphoneNum",iphoneNum);
                user.put("isLogin",isLogin);
                String sign="";
                if (isLogin==1){
                    //生成秘钥  并设置过期时间为七天
                     sign = JWT.sign(user, 1000 * 60 * 60 * 24*7);
                }else{
                    //生成秘钥  并设置过期时间
                    sign = JWT.sign(user, 1000 * 60 * 60 * 24);
                }
                // 加签 手机号加sign值  目的 为了防止篡改数据
                String token = Base64.getEncoder().encodeToString((iphoneNum + "," + sign).getBytes());
                if (isLogin==1){
                    //将最新的秘钥存到redis里， 确保生成的多个秘钥最新的是有用的 并设置过期时间为七天
                    RedisUser.set("token_"+iphoneNum,sign,60*30*2*24*7);
                }else{
                    //将最新的秘钥存到redis里， 确保生成的多个秘钥最新的是有用的
                    RedisUser.set("token_"+iphoneNum,sign,60*30);
                }
                logMap.put("status","200");
                logMap.put("message","登录成功");
                logMap.put("token",token);
            }else{
                //验证码不正确
                logMap.put("status","600");
                logMap.put("message","验证码错误");
            }
        }else{
            //用户不存在
            logMap.put("status","300");
            logMap.put("message","用户不存在");
        }
        return jsonData.getJsonSuccess(logMap);
    }

}
