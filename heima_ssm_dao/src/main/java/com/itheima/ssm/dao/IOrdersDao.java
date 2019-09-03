package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            /*@Result(column = "ordernum",property = "orderNum"),
            @Result(column = "ordertime",property = "orderTime"),
            @Result(column = "peopelcount",property = "peopleCount"),
            @Result(column = "orderdesc",property = "orderDesc"),
            @Result(column = "paytype",property = "payType"),
            @Result(column = "orderstatus",property = "orderStatus"),*/
            @Result(column = "productid",property = "product",
                    javaType = Product.class,
                    one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")
            )
    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "ordernum",property = "orderNum"),
            @Result(column = "ordertime",property = "orderTime"),
            @Result(column = "peopelcount",property = "peopleCount"),
            @Result(column = "orderdesc",property = "orderDesc"),
            @Result(column = "paytype",property = "payType"),
            @Result(column = "orderstatus",property = "orderStatus"),
            @Result(column = "productid",property = "product",
                    javaType = Product.class,
                    one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")
            ),
            @Result(column = "memberid",property = "member",
                    javaType = Member.class,
                    one = @One(select = "com.itheima.ssm.dao.IMemberDao.findById")
            ),
            @Result(column = "id",property = "travellers",
                    javaType = List.class,
                    many = @Many(select = "com.itheima.ssm.dao.ITravellerDao.findByOrdersId")
            )
    })
    public Orders findById(String id) throws Exception;

}
