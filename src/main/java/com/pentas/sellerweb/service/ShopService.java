package com.pentas.sellerweb.service;

import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    CmmnDao cmmnDao;

    public DevMap shopInfo(DevMap param) {
        return cmmnDao.selectOne("sellerweb.shop.shopinfo", param);
    }

}
