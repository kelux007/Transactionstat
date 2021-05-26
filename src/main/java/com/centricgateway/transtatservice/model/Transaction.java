package com.centricgateway.transtatservice.model;

public class Transaction {
	private String amount;
	private String timestamp;

	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return amount;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return
			"TransactionDto{" +
			"amount = '" + amount + '\'' +
			",timestamp = '" + timestamp + '\'' +
			"}";
		}
}
