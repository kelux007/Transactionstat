package com.centricgateway.transtatservice.dao;

import com.centricgateway.transtatservice.model.TransactionQueue;
import com.centricgateway.transtatservice.utility.Utils;
import com.centricgateway.transtatservice.model.Statistics;
import com.centricgateway.transtatservice.model.Transaction;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class TransactionDAOImpl implements TransactionDAO {
    TransactionQueue tramQueue;
    public TransactionDAOImpl() {

    }

    //BUSINESS LOGIC TO GET TRANSACTION STATISTICS
    @Override
    public Statistics getStat() {
        Statistics statistics = new Statistics();
        if(this.tramQueue.transactionQueue.size() <= 0){
            statistics.setSum("0.00");
            statistics.setAvg("0.00");
            statistics.setMax("0.00");
            statistics.setMin("0.00");
            statistics.setCount(0);
            return statistics;
        }

        Double sum = 0.0;
        Double avg = 0.0;
        Double max = 0.0;
        Double min = this.tramQueue.transactionQueue.size() <= 0 ? 0.0 : Double.parseDouble(this.tramQueue.transactionQueue.get(0).getAmount());

        for (int i = 0; i< this.tramQueue.transactionQueue.size(); i++){

            sum = sum + Double.parseDouble(this.tramQueue.transactionQueue.get(i).getAmount());
            if(Double.parseDouble(this.tramQueue.transactionQueue.get(i).getAmount()) > max){
                max = Double.parseDouble(this.tramQueue.transactionQueue.get(i).getAmount());
            }else if(Double.parseDouble(this.tramQueue.transactionQueue.get(i).getAmount()) < min){
                min = Double.parseDouble(this.tramQueue.transactionQueue.get(i).getAmount());
            }
        }
        avg = sum/this.tramQueue.transactionQueue.size();
        BigDecimal bigSum = new BigDecimal(sum);
        BigDecimal bigAvg = new BigDecimal(avg);
        BigDecimal bigMin = new BigDecimal(min);
        BigDecimal bigMax = new BigDecimal(max);
        statistics.setSum(Utils.formatToString(bigSum));
        statistics.setAvg(Utils.formatToString(bigAvg));
        statistics.setMax(Utils.formatToString(bigMax));
        statistics.setMin(Utils.formatToString(bigMin));
        statistics.setCount(this.tramQueue.transactionQueue.size());
        return statistics;
    }


    //BUSINESS LOGIC TO ADD SINGLE TRANSACTION RECORD
    @Override
    public String addTransaction(Transaction transaction) {
        synchronized (this){
            try{
                BigDecimal amount = new BigDecimal(transaction.getAmount());
                System.out.println("Passed amount check");
                TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(transaction.getTimestamp());
                Instant i = Instant.from(ta);
                Date d = Date.from(i);
                System.out.println("Passed Time stamp check");
            }catch (NumberFormatException | DateTimeException nm){
                return "422";
            }
            try {
                if(Utils.isValidDate(transaction.getTimestamp())){
                    System.out.println("Passed future ime stamp check");
                    this.tramQueue.transactionQueue.add(transaction);
                }else{
                    return "204";
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return "204";
            }
            System.out.println("Transactions: " + this.tramQueue.transactionQueue.toString());
            return "201";

        }
    }

    //BUSINESS LOGIC TO DELETE ALL TRANSACTIONS
    @Override
    public boolean deleteAllTransaction() {
        this.tramQueue.transactionQueue.clear();
        System.out.println("Transactions deleted");
        return true;
    }


}
