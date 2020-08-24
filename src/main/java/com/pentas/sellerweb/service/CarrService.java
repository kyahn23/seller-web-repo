package com.pentas.sellerweb.service;

import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrService {

    @Autowired
    CmmnDao cmmnDao;

    public List<DevMap> mntrtList(DevMap param){

        return cmmnDao.selectList("sellerweb.carr.mntrtlist", param);
    }

    public void insertUseMnt(DevMap param){
        cmmnDao.insert("sellerweb.carr.mntrtlist", param);
    }

}
