package com.pentas.sellerweb.service;

import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

//        param.put("BN_NO", "2020082500000000001");                                          // 업체번호
//        param.put("AMD_MBR_ID", "qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwer@naver.com");      // 회원아이디

        param.put("bnNo", "2020082500000000001");                                          // 업체번호
        param.put("AMD_MBR_ID", "aassddff@naver.com");                                      // 회원아이디

        return cmmnDao.selectList("sellerweb.carr.mntrtlist", param);
    }

    public void insertUseMnt(List<DevMap> params) {

//        세션에서 업체번호 및 회원아이디 가져오기
//        HttpSession session = request.getSession();
//        String 업체번호 = (String)session.getAttribute("업체번호);
//        String mbrId = (String)session.getAttribute("회원아이디");

        for (DevMap param : params) {
//            param.put("BN_NO", "2020082500000000001");                                          // 업체번호
//            param.put("AMD_MBR_ID", "qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwer@naver.com");      // 회원아이디
            param.put("BN_NO", "2020090100000000001");                                          // 업체번호
            param.put("AMD_MBR_ID", "aassddff@naver.com");                                      // 회원아이디
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
//            param.put("BN_NO", "2020082500000000001");                                          // 업체번호
//            param.put("AMD_MBR_ID", "qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwer@naver.com");      // 회원아이디

            param.put("BN_NO", "2020090100000000001");                                          // 업체번호
            param.put("AMD_MBR_ID", "aassddff@naver.com");                                      // 회원아이디

            cmmnDao.delete("sellerweb.carr.nUseMntRt", param);

            // 사용여부 내역 남기기
            param.put("useYn", "N");
            cmmnDao.insert("sellerweb.carr.useYnHistory", param);
        }

    }

    public List<DevMap> getSellDeviceList(DevMap param) {
//        세션에서 업체번호 및 회원아이디 가져오기
//        HttpSession session = request.getSession();
//        String 업체번호 = (String)session.getAttribute("업체번호);
//        String mbrId = (String)session.getAttribute("회원아이디");

        param.put("BN_NO", "2020082500000000001");                                          // 업체번호
        param.put("AMD_MBR_ID", "aassddff@naver.com");                                      // 회원아이디

        List<DevMap> sellDeviceList = new ArrayList<>();

        sellDeviceList = cmmnDao.selectList("sellerweb.carr.sellDeviceList2", param);

//        for (DevMap list : sellDeviceList) {
//            String lastModiMbr = "";
//            String lastModiDate = "";
//
//            String tadate = list.getString("tadate", "0");
//            String tbdate = list.getString("tbdate", "0");
//            String tcdate = list.getString("tcdate", "0");
//
//            if (tadate.compareTo(tbdate) >= 0){
//                if (tadate.compareTo(tcdate) >= 0){
//                    lastModiDate = tadate;
//                    list.put("lastModiMbr", list.getString("tambr"));
//                } else {
//                    lastModiDate = tcdate;
//                    list.put("lastModiMbr", list.getString("tcmbr"));
//                }
//            } else if(tadate.compareTo(tbdate) < 0) {
//                if (tbdate.compareTo(tcdate) >= 0){
//                    lastModiDate = tbdate;
//                    list.put("lastModiMbr", list.getString("tbmbr"));
//                } else {
//                    lastModiDate = tcdate;
//                    list.put("lastModiMbr", list.getString("tcmbr"));
//                }
//            }
//            if(lastModiDate == "0") list.put("lastModiDate", null);
//            else list.put("lastModiDate", lastModiDate);
//
//        }

        return sellDeviceList;
    }

    public List<DevMap> getmoveCarrList(DevMap param) {
        List<DevMap> moveCarrList = new ArrayList<>();
        moveCarrList = cmmnDao.selectList("sellerweb.carr.moveCarrList", param);
        return moveCarrList;
    }

    public List<DevMap> getchgDeviceList(DevMap param) {
        List<DevMap> chgDeviceList = new ArrayList<>();
        chgDeviceList = cmmnDao.selectList("sellerweb.carr.chgDeviceList", param);
        return chgDeviceList;
    }

    public List<DevMap> getnewSignUpList(DevMap param) {
        List<DevMap> newSignUpList = new ArrayList<>();
        newSignUpList = cmmnDao.selectList("sellerweb.carr.newSignUpList", param);
        return newSignUpList;
    }
}
