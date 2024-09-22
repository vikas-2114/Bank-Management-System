package com.bms.dao;

import com.bms.Register;
import java.util.List;

public interface RegisterDao {
    // Interface methods
    void saveRegister(Register register);
    Register getRegisterByEmail(String email);
    int getLastUsernameID();
    Register getRegister(int usernameID);
    List<Register> getAllRegisters();
    void updateRegister(Register register);
    void deleteRegister(int usernameID);

    // Remove the saveRegister1 method if not needed
    // void saveRegister1(Register register);
}
