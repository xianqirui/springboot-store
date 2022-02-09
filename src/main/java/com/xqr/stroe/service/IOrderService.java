package com.xqr.stroe.service;

import com.xqr.stroe.entity.Address;
import com.xqr.stroe.entity.Order;

public interface IOrderService {

    Order create(Integer aid, Integer uid, String userName, Integer[] cids);
}
