package com.bms.dao;

import com.bms.Transactionn;
import java.util.List;

public interface TransactionnDao {
    void saveTransactionn(Transactionn transactionn);
    Transactionn getTransactionn(int transactionID);
    List<Transactionn> getAllTransactionns();
    void updateTransactionn(Transactionn transactionn);
    void deleteTransactionn(int transactionID);
    void close(); // Add this method to the interface
}
