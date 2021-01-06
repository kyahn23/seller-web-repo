package com.pentas.sellerweb.common.conf.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("emailProperties")
@PropertySource(value = {"classpath:properties/email.properties"}, encoding = "UTF-8")
public class EmailProperties {

    @Value("${email.smtpHost}")
    private String smtpHost;

    @Value("${email.smtpPort}")
    private String smtpPort;

    @Value("${email.smtpUser}")
    private String smtpUser;

    @Value("${email.smtpPassword}")
    private String smtpPassword;

    @Value("${email.fromEmail}")
    private String fromEmail;

    @Value("${email.fromName}")
    private String fromName;

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUser() {
        return smtpUser;
    }

    public void setSmtpUser(String smtpUser) {
        this.smtpUser = smtpUser;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
}
