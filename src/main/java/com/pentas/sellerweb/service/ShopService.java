package com.pentas.sellerweb.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.exception.UserException;
import com.pentas.sellerweb.common.module.util.DevMap;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ShopService {

    @Autowired
    CmmnDao cmmnDao;

    @Autowired
    CommonService commonService;

    /**
     * 매장정보 조회
     * @param param
     * @return
     */
    public DevMap getShopInfo(DevMap param) {
        return cmmnDao.selectOne("sellerweb.shop.getShopInfo", param);
    }

    /**
     * 매장정보 수정
     * @param param
     * @throws UserException
     */
    public void modiShopInfo(DevMap param) throws UserException { cmmnDao.update("sellerweb.shop.updateShopInfo", param); }

    /**
     * 직원정보 조회
     * @param param
     * @return
     */
    public List<DevMap> getEmpList(DevMap param) { return cmmnDao.selectList("sellerweb.shop.getEmpList", param); }

    /**
     * 직원추가
     * @param param
     * @throws UserException
     */
    public void addEmpInfo(DevMap param) throws UserException { cmmnDao.insert("sellerweb.shop.addEmpInfo", param); }

    /**
     * 직원 퇴사처리 (update)
     * @param param
     * @throws UserException
     */
    public void modiEmpDeact(DevMap param) throws UserException {
        DevMap qParam = new DevMap();
        String amdMbrId = (String) param.get("amdMbrId");
        List<String> bnMbrIdList = (List<String>) param.get("bnMbrIdList");
        for (String bnMbrId : bnMbrIdList) {
            qParam.put("bnMbrId", bnMbrId);
            qParam.put("amdMbrId", amdMbrId);
            cmmnDao.update("sellerweb.shop.updateEmpDeact", qParam);
            qParam.clear();
        }
    }

}
