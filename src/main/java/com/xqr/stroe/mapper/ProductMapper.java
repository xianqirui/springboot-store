package com.xqr.stroe.mapper;

import com.xqr.stroe.entity.Product;

import java.util.List;

public interface ProductMapper {

    List<Product> findHotList();

    Product findById(Integer id);
}
