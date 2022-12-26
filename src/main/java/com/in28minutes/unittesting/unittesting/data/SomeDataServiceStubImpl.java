package com.in28minutes.unittesting.unittesting.data;

public class SomeDataServiceStubImpl implements SomeDataService {

	// stub implementation
	@Override
	public int[] retrieveAllData() {
		return new int[] {1,2,3};
	}
}
