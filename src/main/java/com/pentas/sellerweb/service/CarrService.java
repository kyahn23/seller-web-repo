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
//        세션에서 업체번호 및 회원아이디 가져오기
//        HttpSession session = request.getSession();
//        String 업체번호 = (String)session.getAttribute("업체번호);
//        String mbrId = (String)session.getAttribute("회원아이디");

        param.put("BN_NO", "2020082500000000001");                                          // 업체번호
        param.put("AMD_MBR_ID", "qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwer@naver.com");      // 회원아이디

        return cmmnDao.selectList("sellerweb.carr.mntrtlist", param);
    }

    public void insertUseMnt(List<DevMap> params) {

//        세션에서 업체번호 및 회원아이디 가져오기
//        HttpSession session = request.getSession();
//        String 업체번호 = (String)session.getAttribute("업체번호);
//        String mbrId = (String)session.getAttribute("회원아이디");

        for (DevMap param : params) {
            param.put("BN_NO", "2020082500000000001");                                          // 업체번호
            param.put("AMD_MBR_ID", "qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwer@naver.com");      // 회원아이디
            cmmnDao.insert("sellerweb.carr.useMntRt", param);

            // 사용여부 내역 남기기
            param.put("useYn", "Y");
            cmmnDao.insert("sellerweb.carr.useYnHistory", param);

        }
    }

    public void deleteUseMnt(List<DevMap> params) {

        // 세션에서 업체번호 및 회원아이디 가져오기
        // HttpSession session = request.getSession();
        // String 업체번호 = (String)session.getAttribute("업체번호);
        // String mbrId = (String)session.getAttribute("회원아이디");

        for (DevMap param : params) {
            param.put("BN_NO", "2020082500000000001");                                          // 업체번호
            param.put("AMD_MBR_ID", "qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwer@naver.com");      // 회원아이디
            cmmnDao.delete("sellerweb.carr.nUseMntRt", param);

            // 사용여부 내역 남기기
            param.put("useYn", "N");
            cmmnDao.insert("sellerweb.carr.useYnHistory", param);
        }

    }
}
