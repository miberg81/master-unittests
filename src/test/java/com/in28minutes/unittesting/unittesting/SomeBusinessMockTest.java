package com.in28minutes.unittesting.unittesting;

import com.in28minutes.unittesting.unittesting.business.SomeBusinessImpl;
import com.in28minutes.unittesting.unittesting.data.SomeDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class SomeBusinessMockTest {
	@InjectMocks
	SomeBusinessImpl business = new SomeBusinessImpl();
	@Mock
	SomeDataService dataServiceMock = mock(SomeDataService.class);

	@Test
	public void calculateSumUsingSomeDataService_basic() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1,2,3});
		assertEquals(6, business.calculateSumUsingSomeDataService());
	}

	@Test
	public void calculateSumUsingSomeDataService_empty() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
		assertEquals(0, business.calculateSumUsingSomeDataService());
	}



	@Test
	void calculateSumUsingSomeDataService_oneValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1,2,3});
		assertEquals(6, business.calculateSumUsingSomeDataService());
	}

}
