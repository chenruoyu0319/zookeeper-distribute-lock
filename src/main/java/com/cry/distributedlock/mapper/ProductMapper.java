package com.cry.distributedlock.mapper;

import com.cry.distributedlock.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductMapper {

    @Select(" select * from product where id=#{id}  ")
    Product getProduct(@Param("id") Integer id);

    /**
     * 模拟超卖
     * @param id
     * @return
     */
    @Update(" update product set stock= stock - 1    where id=#{id}  ")
    int reduceStock(@Param("id") Integer id);
}
