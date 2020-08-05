package com.fh.controller;

import com.fh.model.Address;
import com.fh.service.AddressService;
import com.fh.common.jsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("AddressController")
public class AddressController {
    @Autowired
    private AddressService addressService;


    @RequestMapping("selectAdress")
    public jsonData selectAddress(){
        List<Address> addressList=addressService.selectAddress();
        return jsonData.getJsonSuccess(addressList);
    }
}
