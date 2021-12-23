package com.xqr.stroe.controller;


import com.xqr.stroe.entity.Address;
import com.xqr.stroe.service.IAddressService;
import com.xqr.stroe.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController extends BaseController{

    @Autowired
    private IAddressService addressService;

    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }
    @RequestMapping({"/",""})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        System.out.println("刷新成功");
        Integer uid = getuidFromSession(session);
        List<Address> data = addressService.getByuid(uid);
        return new JsonResult<List<Address>>(OK,data);
    }

    /*设置默认值*/
    @RequestMapping("/set_default/{aid}")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session){
        System.out.println("你好");
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.setDefault(aid,uid,username);
        return new JsonResult<>(OK);
    }

    /*删除操作*/
    @RequestMapping("/delete/{aid}")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,HttpSession session){

        addressService.delete(
                aid,
                getuidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
