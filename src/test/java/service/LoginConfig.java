package service;

import org.aeonbits.owner.Config;

@LoginConfig.Sources({"classpath:config/loginConfig.properties"})
public interface LoginConfig extends Config {
    String login();
    String password();
}
