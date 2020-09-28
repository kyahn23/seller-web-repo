package com.pentas.sellerweb.service;

import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.module.mybatis.paginator.domain.PageBounds;
import com.pentas.sellerweb.common.module.mybatis.paginator.domain.PageList;
import com.pentas.sellerweb.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounselService {

    @Autowired
    CmmnDao cmmnDao;

    public PageList<DevMap> counselList(DevMap param) {
        int page = Integer.parseInt(param.getString("page"));
        int pageSize = 10;
        PageBounds pageBounds = new PageBounds(page, pageSize);

        return cmmnDao.selectListPage("sellerweb.counsel.counselList", param, pageBounds);
    }

    public DevMap getCurrPolicy(DevMap param) {
        return cmmnDao.selectOne("sellerweb.counsel.currPolicy", param);
    }

    public List<DevMap> bnMbrList(DevMap param) {

        return cmmnDao.selectList("sellerweb.counsel.bnMbrList", param);
    }

    public void saveCounsel(DevMap param) {
        String visitDt = param.getString("visitDt");
        visitDt = visitDt.concat("00");
        param.put("VISIT_DT", visitDt);

        cmmnDao.update("sellerweb.counsel.saveCounsel", param);
    }

    public void modiVisitDt(DevMap param) {
        String visitDt = param.getString("visitDt");
        visitDt = visitDt.concat("00");
        param.put("VISIT_DT", visitDt);

        cmmnDao.update("sellerweb.counsel.modiVisitDt", param);
    }

    public List<DevMap> useAllCarrMntRt(DevMap param) {
        return cmmnDao.selectList("sellerweb.counsel.useAllCarrMntRt", param);
    }

    public List<DevMap> allDeviceListByCarr(DevMap param) {
        return cmmnDao.selectList("sellerweb.counsel.allDeviceListByCarr", param);
    }

    public void registerRslt(DevMap param) {
        cmmnDao.update("sellerweb.counsel.registerRslt", param);
    }

    /**
     * 클라이언트 회원 마케팅 동의 여부 확인
     * @param param
     * @return
     */
    public String checkClientMarketing(DevMap param) { return cmmnDao.selectOne("sellerweb.counsel.selectClientMarketing", param); }

    /**
     * 마케팅 대상 생성
     * @param param
     */
    public void newMarketingOne(DevMap param) { cmmnDao.insert("sellerweb.counsel.insertMarketingOne", param); }

    /**
     * 마케팅 대상 목록 조회
     *
     * @param param
     * @return
     */
    public PageList<DevMap> marketingList(DevMap param) {
        int page = Integer.parseInt(param.getString("page"));
        int pageSize = 10;
        PageBounds pageBounds = new PageBounds(page, pageSize);

        return cmmnDao.selectListPage("sellerweb.counsel.selectMarketingList", param, pageBounds);
    }

    /**
     * 마케팅 결과 상세 조회
     * @param param
     * @return
     */
    public DevMap marketingOne(DevMap param) {
        return cmmnDao.selectOne("sellerweb.counsel.selectMarketingOne", param);
    }

    /**
     * 요금제 목록 조회
     * @param param
     * @return
     */
    public List<DevMap> monthlyRateList(DevMap param) {
        return cmmnDao.selectList("sellerweb.counsel.selectMonthlyRate", param);
    }

    /**
     * 제조사 목록 조회
     * @param param
     * @return
     */
    public List<String> pnMkr(DevMap param) {
        return cmmnDao.selectList("sellerweb.counsel.selectPnMkr", param);
    }

    /**
     * 기기 모델명으로 제조사 조회
     * @param param
     * @return
     */
    public String pnMkrOne(String param) { return cmmnDao.selectOne("sellerweb.counsel.selectPnMkrOne", param); }

    /**
     * 기기 목록 조회
     * @param param
     * @return
     */
    public List<DevMap> deviceList(DevMap param) {
        return cmmnDao.selectList("sellerweb.counsel.selectDevice", param);
    }

    /**
     * 마케팅 상세 정보 수정
     * @param param
     */
    public void saveMarketingOne(DevMap param) {
        cmmnDao.update("sellerweb.counsel.updateMarketingOne", param);
    }

    /**
     * 마케팅 상세 정보 저장 (신규, 별도 개통)
     * @param param
     */
    public void saveMarketingNew(DevMap param) { cmmnDao.insert("sellerweb.counsel.insertMarketingNew", param); }

}
