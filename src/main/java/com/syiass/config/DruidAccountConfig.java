package com.syiass.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
//自定义的属性的读取 阿里巴巴连接池管理界面的账号和密码
@Component
public class DruidAccountConfig {


    @Value("${duridAccount.loginUsername}")
    private String loginUsername;


    @Value("${duridAccount.loginPassword}")
    private String loginPassword;


    @Override
    public String toString() {
        return "DruidAccountConfig{" +
                "loginUsername='" + loginUsername + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                '}';
    }

    public DruidAccountConfig() {
    }


    public DruidAccountConfig(String loginUsername, String loginPassword) {
        this.loginUsername = loginUsername;
        this.loginPassword = loginPassword;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }




}
