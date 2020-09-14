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
        param.put("BN_NO", "2020082500000000001");                                          // 업체번호
        param.put("AMD_MBR_ID", "qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwer@naver.com");      // 회원아이디

        int page = Integer.parseInt(param.getString("page"));
        int pageSize = 10;
        PageBounds pageBounds = new PageBounds(page, pageSize);

        return cmmnDao.selectListPage("sellerweb.counsel.counselList", param, pageBounds);
    }

    public DevMap getCurrPolicy(DevMap param) {
        param.put("BN_NO", "2020082500000000001");                                          // 업체번호
        param.put("AMD_MBR_ID", "qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwer@naver.com");      // 회원아이디

        return cmmnDao.selectOne("sellerweb.counsel.currPolicy", param);

    }

    public List<DevMap> bnMbrList(DevMap param) {

        return cmmnDao.selectList("sellerweb.counsel.bnMbrList", param);
    }
}
