package com.centricgateway.transtatservice.dao;

import com.centricgateway.transtatservice.model.Statistics;
import com.centricgateway.transtatservice.model.Transaction;
import com.centricgateway.transtatservice.model.TransactionQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TransactionDAOImplTest {
    @Mock
    TransactionQueue tramQueue;
    @InjectMocks
    TransactionDAOImpl transactionDAOImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetStat() {
        Transaction transaction = new Transaction();
        transaction.setAmount("1320252.3343");
        transaction.setTimestamp("2018-07-17T09:59:51.312Z");
        transactionDAOImpl.addTransaction(transaction);
        Statistics result = transactionDAOImpl.getStat();
        Assertions.assertEquals("1,320,252.33", result.getMax());
    }

    @Test
    void testAddTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount("1000.3343");
        transaction.setTimestamp("2018-07-17T09:59:51.312Z");
        String result = transactionDAOImpl.addTransaction(transaction);
        Assertions.assertEquals("201", result);
    }

    @Test
    void testDeleteAllTransaction() {
        boolean result = transactionDAOImpl.deleteAllTransaction();
        Assertions.assertEquals(true, result);
    }
}
