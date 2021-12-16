package service;

import org.aeonbits.owner.Config;

//@LoginConfig.Sources({"classpath:config/credentials.properties"})
public interface LoginConfig extends Config {
    String login();
    String password();
}
