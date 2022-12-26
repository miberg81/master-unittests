package com.in28minutes.unittesting.unittesting.business;

import com.in28minutes.unittesting.unittesting.data.SomeDataService;

import java.util.Arrays;

public class SomeBusinessImpl {
	
	private SomeDataService someDataService;

	public void setSomeDataService(SomeDataService someDataService) {
		this.someDataService = someDataService;
	}

	public int calculateSum(int[] data) {
//		int sum=0;
//		for(int value:data) {
//			sum+=value;
//		}
//		return sum;

		//return Arrays.stream(data).reduce(Integer::sum).orElse(0);

		return Arrays.stream(data).sum();
	}
	
	public int calculateSumUsingSomeDataService() {
		int[] data = someDataService.retrieveAllData();
		int sum=0;
		for(int value:data) {
			sum+=value;
		}
		return sum;
	}
}
