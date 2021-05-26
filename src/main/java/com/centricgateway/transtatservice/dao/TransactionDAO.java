package com.centricgateway.transtatservice.dao;

import com.centricgateway.transtatservice.model.Statistics;
import com.centricgateway.transtatservice.model.Transaction;

public interface TransactionDAO {

    public Statistics getStat();

    public String addTransaction(Transaction transaction);

    public boolean deleteAllTransaction();

}
