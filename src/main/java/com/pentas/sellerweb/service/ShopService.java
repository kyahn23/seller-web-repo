package com.pentas.sellerweb.service;

import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.exception.UserException;
import com.pentas.sellerweb.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void modiShopInfo(DevMap param) throws UserException {
        // 기존 업로드 이미지 삭제처리 (update)
        DevMap qParam = new DevMap();
        List<String> oldImageList = (List<String>) param.get("oldImageList");
        for (String oldImage : oldImageList) {
            qParam.put("storFilNm", oldImage);
            commonService.updateFileDelYn(qParam);
            qParam.clear();
        }

        cmmnDao.update("sellerweb.shop.updateShopInfo", param);
    }

    /**
     * 직원정보 조회
     * @param param
     * @return getEmpList
     */
    public List<DevMap> getEmpList(DevMap param) { return cmmnDao.selectList("sellerweb.shop.getEmpList", param); }

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
     * 매장 공지사항 게시여부 숨김으로 변경
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

    /**
     * 매장 공지사항 글 가져오기
     * @param param
     * @return
     */
    public DevMap getBnBrdOne(DevMap param) {
        DevMap bnBrdOne = new DevMap();
        bnBrdOne = cmmnDao.selectOne("sellerweb.shop.getBnBrdOne", param);

        // 첨부파일 정보 가져오기
        String brdAttFile1 = "";
        String brdAttFile2 = "";
        if (!bnBrdOne.isEmpty()) {
            brdAttFile1 = bnBrdOne.getString("brdAttFile1");
            brdAttFile2 = bnBrdOne.getString("brdAttFile2");
        }

        List<DevMap> brdOneCurFileInfo = new ArrayList<>();
        if (!"".equals(brdAttFile1) && brdAttFile1 != null) {
            DevMap brdOneFile1 = new DevMap();
            brdOneFile1 = commonService.getFileInfo(brdAttFile1);
            brdOneFile1.put("filStat", "CUR");
            brdOneCurFileInfo.add(brdOneFile1);
        }
        if (!"".equals(brdAttFile2) && brdAttFile2 != null) {
            DevMap brdOneFile2 = new DevMap();
            brdOneFile2 = commonService.getFileInfo(brdAttFile2);
            brdOneFile2.put("filStat", "CUR");
            brdOneCurFileInfo.add(brdOneFile2);
        }

        DevMap rslt = new DevMap();
        rslt.put("bnBrdOne", bnBrdOne);
        rslt.put("brdOneCurFileInfo", brdOneCurFileInfo);
        return rslt;
    }

    /**
     * 매장 공지사항 글 수정
     * @param param
     */
    public void modiBnBrdOne(DevMap param) { cmmnDao.update("sellerweb.shop.updateBnBrdOne", param); }

}
