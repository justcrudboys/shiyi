package com.lvpaul.shiyi.user.service;

public interface LoginService {
    public boolean checkUserLogin(String phone,String password);
    public Long getIdByPhone(String phone);
}
