package com.pentas.sellerweb.common.conf.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("settingProperties")
public class SettingProperties {

    @Value("${file.store.path}")
    private String fileStorePath;

    public String getFileStorePath() {
        return fileStorePath;
    }

    public void setFileStorePath(String fileStorePath) {
        this.fileStorePath = fileStorePath;
    }

}
