package com.xqr.stroe.service;

import com.xqr.stroe.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findHotList();

    Product findById(Integer id);
}
