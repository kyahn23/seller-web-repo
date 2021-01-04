package com.pentas.sellerweb.service;

import com.pentas.sellerweb.common.conf.properties.EmailProperties;
import com.pentas.sellerweb.common.module.util.email.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@EnableAsync
@Service
public class EmailService {
    @Autowired
    EmailProperties emailProperties;

    @Async
    public void sendTempPwdEmail(String mbrEmail, String mbrNm, String tempPwd){
        EmailUtil.sendMailAuthSSL(
                emailProperties.getSmtpHost(),
                emailProperties.getSmtpPort(),
                emailProperties.getSmtpUser(),
                emailProperties.getSmtpPassword(),
                "[Pentaworks Service] 임시 비밀번호 발급 안내",
                "<html><p>" + mbrNm + "님의 임시 비밀번호가 발급되었습니다.<br>" +
                        "임시 비밀번호로 로그인 후 새 비밀번호로 변경해주세요.<br></p>" +
                        "<h4>임시 비밀번호 : " + tempPwd + "</h4></html>",
                mbrEmail + "",
                emailProperties.getFromEmail(),
                emailProperties.getFromName()
        );
    }
}
