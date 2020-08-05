package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Shop;
import com.fh.model.vo.ShopCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShopDao extends BaseMapper<Shop> {

    ShopCart queryShopCartById(Integer proId);

    Shop selectListById(Integer shopId);

    int updateShop(@Param("shopId") Integer shopId, @Param("count") Integer count);
}
