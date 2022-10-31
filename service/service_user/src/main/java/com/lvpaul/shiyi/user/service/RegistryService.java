package com.lvpaul.shiyi.user.service;

public interface RegistryService {
    public boolean isPhoneValidate(String phone);
    public boolean isUserNameValidate(String username);
    public boolean userRegister(String phone,String username,String password);
}
