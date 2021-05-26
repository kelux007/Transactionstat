package com.centricgateway.transtatservice.api;

import com.centricgateway.transtatservice.dao.TransactionDAO;
import com.centricgateway.transtatservice.model.Statistics;
import com.centricgateway.transtatservice.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class TranStatResourceTest {
    @Mock
    Transaction transaction;
    @Mock
    Statistics statistics;
    @Mock
    Response.ResponseBuilder responseBuilder;
    @Mock
    TransactionDAO transactionDAO;
    @InjectMocks
    TranStatResource tranStatResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testStatistics() {
        when(transactionDAO.getStat()).thenReturn(new Statistics());

        Response result = tranStatResource.statistics();
        Assertions.assertEquals("Statistics{avg = '0.00',min = '0.00',max = '0.00',count = '0',sum = '0.00'}", result.getEntity().toString());
    }

    @Test
    void testAddTransactions() {
        when(transactionDAO.addTransaction(any())).thenReturn("addTransactionResponse");
        String request = "{\n" +
                "\"amount\":\"1320252.3343\",\n" +
                "\"timestamp\":\"2018-07-17T09:59:51.312Z\"\n" +
                "}";
        Response result = tranStatResource.addTransactions(request);
        Assertions.assertEquals(201, result.getStatus());
    }

    @Test
    void testDeleteAllTransactions() {
        when(transactionDAO.deleteAllTransaction()).thenReturn(true);

        Response result = tranStatResource.deleteAllTransactions();
        Assertions.assertEquals(204, result.getStatus());
    }
}
