package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Order;
import com.fh.model.OrderCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDao extends BaseMapper<Order> {
    void addBatch(@Param("list") List<OrderCart> list, @Param("id") Integer id);
}
