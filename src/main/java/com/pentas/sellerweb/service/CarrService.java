package com.pentas.sellerweb.service;

import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrService {

    @Autowired
    CmmnDao cmmnDao;

    public List<DevMap> mntrtList(DevMap param) {
        return cmmnDao.selectList("sellerweb.carr.mntrtlist", param);
    }

    public void insertUseMnt(List<DevMap> params) {
        for (DevMap param : params) {
            cmmnDao.insert("sellerweb.carr.useMntRt", param);

            // 사용여부 내역 남기기
            param.put("useYn", "Y");
            cmmnDao.insert("sellerweb.carr.useYnHistory", param);

        }
    }

    public void deleteUseMnt(List<DevMap> params) {
        List<DevMap> mvCarrPolicyList = new ArrayList<>();
        List<DevMap> chgDevPolicyList = new ArrayList<>();
        List<DevMap> newSignupPolicyList = new ArrayList<>();

        for (DevMap param : params) {
            //미사용처리전 해당 요금제 판매중지처리 및 판매중지내역남기기
            mvCarrPolicyList = cmmnDao.selectList("sellerweb.carr.policyListByMntRtMvCarr", param);
            if (mvCarrPolicyList.size() != 0) {
                for (DevMap mvCarr : mvCarrPolicyList) {
                    // 판매중지처리를 위한 객체 값 설정
                    mvCarr.put("SELL_YN", "N");
                    mvCarr.put("bnMbrId", param.getString("bnMbrId"));

                    mvCarr.put("policyType", "MoveCarr");
                    // 판매중지처리
                    cmmnDao.update("sellerweb.carr.updateSalePolicyMvCarr", mvCarr);
                    // 판매중지내역남기기
                    cmmnDao.insert("sellerweb.carr.policySaleHistory", mvCarr);
                }
            }
            //미사용처리전 해당 요금제 판매중지처리 및 판매중지내역남기기  policyListByMntRtNewSignup
            chgDevPolicyList = cmmnDao.selectList("sellerweb.carr.policyListByMntRtChgDev", param);
            if (chgDevPolicyList.size() != 0) {
                for (DevMap chgDev : chgDevPolicyList) {
                    // 판매중지처리를 위한 객체 값 설정
                    chgDev.put("SELL_YN", "N");
                    chgDev.put("bnMbrId", param.getString("bnMbrId"));

                    chgDev.put("policyType", "ChgDev");
                    // 판매중지처리
                    cmmnDao.update("sellerweb.carr.updateSalePolicyChgDev", chgDev);
                    // 판매중지내역남기기
                    cmmnDao.insert("sellerweb.carr.policySaleHistory", chgDev);
                }
            }

            //미사용처리전 해당 요금제 판매중지처리 및 판매중지내역남기기  policyListByMntRtNewSignup
            newSignupPolicyList = cmmnDao.selectList("sellerweb.carr.policyListByMntRtNewSignup", param);
            if (newSignupPolicyList.size() != 0) {
                for (DevMap newSignup : newSignupPolicyList) {
                    // 판매중지처리를 위한 객체 값 설정
                    newSignup.put("SELL_YN", "N");
                    newSignup.put("bnMbrId", param.getString("bnMbrId")); // 로그인한 회원아이디

                    newSignup.put("policyType", "NewSignup");
                    // 판매중지처리
                    cmmnDao.update("sellerweb.carr.updateSalePolicyNewSignup", newSignup);
                    // 판매중지내역남기기
                    cmmnDao.insert("sellerweb.carr.policySaleHistory", newSignup);
                }
            }

            // 요금제 미사용처리
            cmmnDao.delete("sellerweb.carr.nUseMntRt", param);

            // 사용여부 내역 남기기
            param.put("useYn", "N");
            cmmnDao.insert("sellerweb.carr.useYnHistory", param);
        }

    }

    public List<DevMap> getSellDeviceList(DevMap param) {
        List<DevMap> sellList = new ArrayList<>();
        sellList = cmmnDao.selectList("sellerweb.carr.sellDeviceList", param);

        for (DevMap list : sellList) {
            if (list.getString("sellStatus") == null){
                if (list.getString("lastModiDate") == null){
                    list.put("sellStatus", "N");    // 판매이력없음(=미판매)
                } else {
                    list.put("sellStatus", "P");    // 판매이력 있음(=판매중지)
                }
            }
        }

        return sellList;
    }

    public List<DevMap> getmoveCarrList(DevMap param) {
        List<DevMap> moveCarrList = new ArrayList<>();
        moveCarrList = cmmnDao.selectList("sellerweb.carr.moveCarrList", param);

        for (DevMap list : moveCarrList) {
            String tmp = "";
            if (list.getString("sellYn") == null) {
                list.put("saveType", "insert");
            } else {
                list.put("saveType", "update");
            }
        }
        return moveCarrList;
    }

    public List<DevMap> getchgDeviceList(DevMap param) {
        List<DevMap> chgDeviceList = new ArrayList<>();
        chgDeviceList = cmmnDao.selectList("sellerweb.carr.chgDeviceList", param);

        for (DevMap list : chgDeviceList) {
            String tmp = "";
            if (list.getString("sellYn") == null) {
                list.put("saveType", "insert");
            } else {
                list.put("saveType", "update");
            }
        }
        return chgDeviceList;
    }

    public List<DevMap> getnewSignUpList(DevMap param) {
        List<DevMap> newSignUpList = new ArrayList<>();
        newSignUpList = cmmnDao.selectList("sellerweb.carr.newSignUpList", param);

        for (DevMap list : newSignUpList) {
            String tmp = "";
            if (list.getString("sellYn") == null) {
                list.put("saveType", "insert");
            } else {
                list.put("saveType", "update");
            }
        }

        return newSignUpList;
    }

    public void saveSalePolicyMoveCarr(List<DevMap> params) {
        for (DevMap param : params) {
            param.put("policyType", "MoveCarr");    // 정책유형

            String saveType = param.getString("saveType");
            String saveYn = param.getString("saveYn");
            if ("Y".equals(saveYn)) {
                if ("insert".equals(saveType)) {
                    cmmnDao.insert("sellerweb.carr.insertSalePolicyMvCarr", param);
                } else if ("update".equals(saveType)) {
                    cmmnDao.update("sellerweb.carr.updateSalePolicyMvCarr", param);
                }
                // 정책 저장 내역 남기기
                cmmnDao.insert("sellerweb.carr.policySaleHistory", param);
            }
        }
    }

    public void saveSalePolicyChgDev(List<DevMap> params) {
        for (DevMap param : params) {
            param.put("policyType", "ChgDev");    // 정책유형

            String saveType = param.getString("saveType");
            String saveYn = param.getString("saveYn");
            if ("Y".equals(saveYn)) {
                if ("insert".equals(saveType)) {
                    cmmnDao.insert("sellerweb.carr.insertSalePolicyChgDev", param);
                } else if ("update".equals(saveType)) {
                    cmmnDao.update("sellerweb.carr.updateSalePolicyChgDev", param);
                }
                // 정책 저장 내역 남기기
                cmmnDao.insert("sellerweb.carr.policySaleHistory", param);
            }
        }
    }

    public void saveSalePolicyNewSignup(List<DevMap> params) {
        for (DevMap param : params) {
            param.put("policyType", "NewSignup");    // 정책유형

            String saveType = param.getString("saveType");
            String saveYn = param.getString("saveYn");
            if ("Y".equals(saveYn)) {
                if ("insert".equals(saveType)) {
                    cmmnDao.insert("sellerweb.carr.insertSalePolicyNewSignup", param);
                } else if ("update".equals(saveType)) {
                    cmmnDao.update("sellerweb.carr.updateSalePolicyNewSignup", param);
                }
                // 정책 저장 내역 남기기
                cmmnDao.insert("sellerweb.carr.policySaleHistory", param);
            }
        }
    }

    public void stopSaleDevice(List<DevMap> params) {
        List<DevMap> stopSaleDevMvCarrList = new ArrayList<>();
        List<DevMap> stopSaleDevChgDevList = new ArrayList<>();
        List<DevMap> stopSaleDevNewSignupList = new ArrayList<>();

        for (DevMap param : params) {
            // 번호이동
            cmmnDao.update("sellerweb.carr.stopSaleDevMvCarr", param);
            // SellCondNo 를 얻기위한 리스트
            stopSaleDevMvCarrList = cmmnDao.selectList("sellerweb.carr.stopSaleDevMvCarrList", param);
            if (stopSaleDevMvCarrList.size() != 0) {
                for (DevMap list : stopSaleDevMvCarrList) {
                    list.put("SELL_COND_TYPE", "A");
                    list.put("policyType", "MoveCarr");
                    cmmnDao.insert("sellerweb.carr.policySaleHistory", list);
                }
            }
        }

        for (DevMap param : params) {
            // 기기변경
            cmmnDao.update("sellerweb.carr.stopSaleDevChgDev", param);

            stopSaleDevChgDevList = cmmnDao.selectList("sellerweb.carr.stopSaleDevChgDevList", param);
            if (stopSaleDevChgDevList.size() != 0) {
                for (DevMap list : stopSaleDevChgDevList) {
                    list.put("SELL_COND_TYPE", "B");
                    list.put("policyType", "ChgDev");
                    cmmnDao.insert("sellerweb.carr.policySaleHistory", list);
                }
            }
        }

        for (DevMap param : params) {
            // 신규가입
            cmmnDao.update("sellerweb.carr.stopSaleDevNewSignup", param);

            stopSaleDevNewSignupList = cmmnDao.selectList("sellerweb.carr.stopSaleDevNewSignupList", param);
            if (stopSaleDevNewSignupList.size() != 0) {
                for (DevMap list : stopSaleDevNewSignupList) {
                    list.put("SELL_COND_TYPE", "C");
                    list.put("policyType", "NewSignup");
                    cmmnDao.insert("sellerweb.carr.policySaleHistory", list);
                }
            }
        }

    }
}
