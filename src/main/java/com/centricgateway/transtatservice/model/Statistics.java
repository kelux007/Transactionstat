package com.centricgateway.transtatservice.model;

public class Statistics{
	private String avg;
	private String min;
	private String max;
	private Integer count;
	private String sum;

	public void setAvg(String avg){
		this.avg = avg;
	}

	public String getAvg(){
		return avg;
	}

	public void setMin(String min){
		this.min = min;
	}

	public String getMin(){
		return min;
	}

	public void setMax(String max){
		this.max = max;
	}

	public String getMax(){
		return max;
	}

	public void setCount(Integer count){
		this.count = count;
	}

	public Integer getCount(){
		return count;
	}

	public void setSum(String sum){
		this.sum = sum;
	}

	public String getSum(){
		return sum;
	}

	@Override
 	public String toString(){
		return 
			"Statistics{" + 
			"avg = '" + avg + '\'' + 
			",min = '" + min + '\'' + 
			",max = '" + max + '\'' + 
			",count = '" + count + '\'' + 
			",sum = '" + sum + '\'' + 
			"}";
		}
}
