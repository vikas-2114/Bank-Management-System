package com.bms.dao;

import com.bms.Login;
import java.util.List;

public interface LoginDao {
    void saveLogin(Login login);
    Login getLogin(String email);
    List<Login> getAllLogins();
    void updateLogin(Login login);
    void deleteLogin(String email);
}
