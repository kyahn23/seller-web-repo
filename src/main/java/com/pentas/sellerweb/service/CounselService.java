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
}
