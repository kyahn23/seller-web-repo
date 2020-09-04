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
     * @return getShopInfo
     */
    public DevMap getShopInfo(DevMap param) {
        return cmmnDao.selectOne("sellerweb.shop.getShopInfo", param);
    }

    /**
     * 매장정보 수정
     * @param param
     */
    public void modiShopInfo(DevMap param) { cmmnDao.update("sellerweb.shop.updateShopInfo", param); }

    /**
     * 직원정보 조회
     * @param param
     * @return getEmpList
     */
    public List<DevMap> getEmpList(DevMap param) { return cmmnDao.selectList("sellerweb.shop.getEmpList", param); }

    /**
     * 직원추가
     * @param param
     */
    public void addEmpInfo(DevMap param) { cmmnDao.insert("sellerweb.shop.addEmpInfo", param); }

    /**
     * 직원 퇴사처리 (update)
     * @param param
     */
    public void modiEmpDeact(DevMap param) {
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

    /**
     * 직원 비밀번호 재발급
     * @param param
     */
    public void modiEmpPwd(DevMap param) { cmmnDao.update("sellerweb.shop.updateEmpPwd", param); }

    /**
     * 직원 권한 수정
     * @param param
     */
    public void modiEmpPrms(DevMap param) { cmmnDao.update("sellerweb.shop.updateEmpPrms", param); }

    /**
     * 매장 공지사항 글 작성
     * @param param
     */
    public void addBnBrd(DevMap param) { cmmnDao.insert("sellerweb.shop.addBnBrd", param); }

    /**
     * 매장 공지사항 목록 가져오기
     * @param param
     * @return
     */
    public List<DevMap> getBnBrdList(DevMap param) { return cmmnDao.selectList("sellerweb.shop.getBnBrdList", param); }

    /**
     * 매장 공지사항 게시여부 수정
     * @param param
     */
    public void modiBnBrdHide(DevMap param) {
        DevMap qParam = new DevMap();
        String bnMbrId = (String) param.get("bnMbrId");
        List<Integer> brdNoList = (List<Integer>) param.get("brdNoList");
        for (Integer brdNo : brdNoList) {
            qParam.put("bnMbrId", bnMbrId);
            qParam.put("brdNo", brdNo);
            cmmnDao.update("sellerweb.shop.updateBnBrdHide", qParam);
            qParam.clear();
        }
    }

}
