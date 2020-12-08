package com.pentas.sellerweb.service;

import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BatchService {

    @Autowired
    CmmnDao cmmnDao;

    /**
     *  매일 23시 50분 수행
     */
//    @Scheduled(cron = "0 50 23 * * *")
//    public void lowprice(){
//        DevMap rslt = new DevMap();
//
//        List<DevMap> lpList = new ArrayList<>();
//        lpList = cmmnDao.selectList("sellerweb.counsel.lplist");    // 최저가
//
//        for (DevMap lp : lpList) {
//            cmmnDao.insert("sellerweb.counsel.insertLplist", lp);
//        }
//
//    }
}
