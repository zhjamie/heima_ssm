package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll(Integer page,Integer size) throws Exception;

    void save(Product product) throws Exception;

}
