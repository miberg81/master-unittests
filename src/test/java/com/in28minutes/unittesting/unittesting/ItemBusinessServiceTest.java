package com.in28minutes.unittesting.unittesting;

import com.in28minutes.unittesting.unittesting.business.ItemBusinessService;
import com.in28minutes.unittesting.unittesting.data.ItemRepository;
import com.in28minutes.unittesting.unittesting.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ItemBusinessServiceTest {
	@InjectMocks
	private ItemBusinessService business;

	@Mock
	private ItemRepository repository;

	@Test
	public void retrieveAllItems_basic() {
		// we stub the repository logic...
		when(repository.findAll()).thenReturn(Arrays.asList(
				new Item(1, "Item1", 10, 1),
				new Item(2, "Item2", 20, 2),
				new Item(3, "Item3", 30, 3),
				new Item(4, "Item4", 40, 4)
		));
		
		// but we check if the business logic works fine...
		List<Item> items = business.retrieveAllItems();
		assertEquals(10, items.get(0).getValue());
		assertEquals(40, items.get(1).getValue());
		assertEquals(90, items.get(2).getValue());
		assertEquals(160, items.get(3).getValue());
	}
}
