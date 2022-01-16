package service;

import org.aeonbits.owner.Config;

@LoginConfig.Sources({"classpath:config/loginConfig.properties"})
public interface LoginConfig extends Config {
    String loginFromTest();
    String passwordFromTest();
    String login();
    String password();
}
