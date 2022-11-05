package com.lvpaul.shiyi.user.service;

public interface LoginService {
    public boolean checkUserLoginByPhone(String phone,String password);
    public boolean checkUserLoginByName(String phone,String password);
    public Long getIdByPhone(String phone);
    public Long getIdByName(String username);
}
