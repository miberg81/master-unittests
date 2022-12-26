package com.in28minutes.unittesting.unittesting.controller;

import com.in28minutes.unittesting.unittesting.business.ItemBusinessService;
import com.in28minutes.unittesting.unittesting.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // test fails without this ,because only the itemsController bean is loaded, without dependencies
    // this annotation will bring mock dependency, returning null by default
    @MockBean
    private ItemBusinessService itemBusinessService;

    // this item is returned directly, without service dependency
    @Test
    public void testItem_basic() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);

        // .json can match responses even with additional spaces and even with some
        // nested elements missing (can omit quantity) while .string is expecting exact results!
        final MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10}"))
                .andReturn();

        // what happens under the hood for .json:
        // JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), strict);
    }


    @Test
    public void itemFromBusinessService_basic() throws Exception {
        when(itemBusinessService.retrieveHardcodedItem())
                .thenReturn(new Item(2, "Item2", 10, 10));

        final RequestBuilder request = MockMvcRequestBuilders
                .get("/item-from-business-service")
                .accept(MediaType.APPLICATION_JSON);

        final MvcResult perform = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{id: 2,name: Item2,price: 10}"))
                .andReturn();
    }

    @Test
    public void retrieveAllItems_basic() throws Exception {
        when(itemBusinessService.retrieveAllItems())
                .thenReturn(Arrays.asList(
                        new Item(1, "Item1", 10, 1),
                        new Item(2, "Item2", 20, 2),
                        new Item(3, "Item3", 30, 3),
                        new Item(4, "Item4", 40, 4)
                ));

        final RequestBuilder request = MockMvcRequestBuilders
                .get("/all-items-from-database")
                .accept(MediaType.APPLICATION_JSON);

        // must expect exactly the structure, but some elements may be left empty
        // elements may be expected not in the same order  (strict=false)
        final MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[" +
                        "{id: 1,name: Item1,price: 10}," +
                        "{}," +
                        "{id: 3,name: Item3,price: 30}," +
                        "{}" +
                        "]")) // passes
                .andReturn();
    }
}
