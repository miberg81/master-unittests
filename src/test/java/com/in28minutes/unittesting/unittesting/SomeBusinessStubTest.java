package com.in28minutes.unittesting.unittesting;

import com.in28minutes.unittesting.unittesting.business.SomeBusinessImpl;
import com.in28minutes.unittesting.unittesting.data.SomeDataService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SomeBusinessStubTest {
	// all 3 tests need different data!
	@Nested
	class TestWithDataFromDatService {
		@Test
		public void calculateSumUsingSomeDataService_basic() {
			SomeBusinessImpl business = new SomeBusinessImpl();
			// unit test should not talk to db! 
			// because they would break if data is changed in db
			// so unit tests should not depend on data outside
			business.setSomeDataService(new SomeDataServiceStub());
			int actualResult = business.calculateSumUsingSomeDataService();
			int expectedResult = 6;
			assertEquals(expectedResult, actualResult);
		}

		@Test
		public void calculateSumUsingSomeDataService_empty() {
			SomeBusinessImpl business = new SomeBusinessImpl();
			business.setSomeDataService(new SomeDataServiceEmptyStub());
			int actualResult = business.calculateSumUsingSomeDataService();
			int expectedResult = 0;
			assertEquals(expectedResult, actualResult);
		}

		@Test
		void calculateSumUsingSomeDataService_oneValue() {
			SomeBusinessImpl business = new SomeBusinessImpl();
			business.setSomeDataService(new SomeDataServiceOneValueStub());
			int actualResult = business.calculateSumUsingSomeDataService();
			int expectedResult = 5;
			assertEquals(expectedResult, actualResult);
		}
	}

	class SomeDataServiceStub implements SomeDataService {
		@Override
		public int[] retrieveAllData() {
			return new int[] {1,2,3};
		}
	}

	class SomeDataServiceEmptyStub implements SomeDataService {
		@Override
		public int[] retrieveAllData() {
			return new int[] {};
		}
	}
	class SomeDataServiceOneValueStub implements SomeDataService {
		@Override
		public int[] retrieveAllData() {
			return new int[] {5};
		}
	}
}
