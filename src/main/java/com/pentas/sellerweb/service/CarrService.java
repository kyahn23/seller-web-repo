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

    public List<DevMap> mntrtList(DevMap param) {

        return cmmnDao.selectList("sellerweb.carr.mntrtlist", param);
    }

    public void insertUseMnt(List<DevMap> params) {

//        세션에서 업체번호 및 회원아이디 가져오기
//        HttpSession session = request.getSession();
//        String 업체번호 = (String)session.getAttribute("업체번호);
//        String mbrId = (String)session.getAttribute("회원아이디");

        for (DevMap param:params){
            param.put("BN_NO", "2020082500000000001");                                          // 업체번호
            param.put("AMD_MBR_ID", "qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwer@naver.com");      // 회원아이디
            cmmnDao.insert("sellerweb.carr.useMntRt", param);
        }
    }

    public void deleteUseMnt(List<DevMap> params) {

        for (DevMap param:params){
            cmmnDao.delete("sellerweb.carr.nUseMntRt", param);
        }

    }
}
