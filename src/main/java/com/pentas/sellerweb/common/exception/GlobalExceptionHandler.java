package com.pentas.sellerweb.common.exception;

import com.pentas.sellerweb.common.module.util.CmmnUtil;
import com.pentas.sellerweb.common.module.util.DevMap;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@ControllerAdvice  
@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DevException.class)  
    public DevMap handleDevException(DevException e){
    	log.error("DevException ====> ");
    	log.error(CmmnUtil.getExceptionMsg(e));
    	
        return new DevMap() {{put("rsltStat", "dev-error"); put("errMsg", e.getMessage());}};
    }
    
    @ExceptionHandler(value = Exception.class)  
    public DevMap handleException(Exception e){
    	log.error("Exception ====> ");
    	log.error(CmmnUtil.getExceptionMsg(e));
    	return new DevMap() {{put("rsltStat", "error");}};
    }
}
