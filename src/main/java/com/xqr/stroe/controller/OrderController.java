package com.xqr.stroe.controller;

import com.xqr.stroe.entity.Order;
import com.xqr.stroe.service.IOrderService;
import com.xqr.stroe.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/create")
    public JsonResult<Order> creat(Integer aid, Integer[] cids, HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        Order data = orderService.create(aid, uid, username, cids);
        return new JsonResult<>(OK,data);
    }
}
