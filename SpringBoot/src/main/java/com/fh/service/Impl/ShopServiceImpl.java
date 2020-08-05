package com.fh.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.dao.ShopDao;
import com.fh.model.Shop;
import com.fh.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    public List<Shop> selectShop() {
        return   shopDao.selectList(null);
    }

    @Override
    public List<Shop> selectShopSale() {
        QueryWrapper<Shop> qw=new QueryWrapper<>();
        qw.eq("shopSale",1);
        return shopDao.selectList(qw);
    }

    @Override
    public List<Shop> selectShopByTypeId(String typeId) {
        QueryWrapper<Shop> qw=new QueryWrapper<>();
        if (StringUtils.isEmpty(typeId)){
            return shopDao.selectList(null);
        }else{
            qw.like("shopType",typeId+"%");
            List<Shop> shops = shopDao.selectList(qw);
            return shops;
        }

    }
}
