package com.pentas.sellerweb.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.exception.UserException;
import com.pentas.sellerweb.common.module.util.DevMap;
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

}
