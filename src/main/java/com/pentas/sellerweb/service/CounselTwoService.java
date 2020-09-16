package com.pentas.sellerweb.service;

import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.module.mybatis.paginator.domain.PageBounds;
import com.pentas.sellerweb.common.module.mybatis.paginator.domain.PageList;
import com.pentas.sellerweb.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounselTwoService {

    @Autowired
    CmmnDao cmmnDao;

    /**
     * 마케팅 대상 목록 조회
     * @param param
     * @return
     */
    public PageList<DevMap> marketingList(DevMap param) {
        int page = Integer.parseInt(param.getString("page"));
        int pageSize = 10;
        PageBounds pageBounds = new PageBounds(page, pageSize);

        return cmmnDao.selectListPage("sellerweb.counseltwo.selectMarketingList", param, pageBounds);
    }

    /**
     * 마케팅 결과 상세 조회
     * @param param
     * @return
     */
    public DevMap marketingOne(DevMap param) {
        return cmmnDao.selectOne("sellerweb.counseltwo.selectMarketingOne", param);
    }

    /**
     * 요금제 목록 조회
     * @param param
     * @return
     */
    public List<DevMap> monthlyRateList(DevMap param) {
        return cmmnDao.selectList("sellerweb.counseltwo.selectMonthlyRate", param);
    }

    /**
     * 제조사 목록 조회
     * @param param
     * @return
     */
    public List<String> pnMkr(DevMap param) {
        return cmmnDao.selectList("sellerweb.counseltwo.selectPnMkr", param);
    }

    /**
     * 기기 목록 조회
     * @param param
     * @return
     */
    public List<DevMap> deviceList(DevMap param) {
        return cmmnDao.selectList("sellerweb.counseltwo.selectDevice", param);
    }

    /**
     * 마케팅 상세 정보 저장
     * @param param
     */
    public void saveMarketingOne(DevMap param) {
        cmmnDao.update("sellerweb.counseltwo.updateMarketingOne", param);
    }

}
