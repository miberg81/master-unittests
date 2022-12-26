package com.in28minutes.unittesting.unittesting.controller;

import com.in28minutes.unittesting.unittesting.business.ItemBusinessService;
import com.in28minutes.unittesting.unittesting.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// WebLayer = controller (talking to business service)
// BusinessLayer = service (talking to DB)
// DB layer = Hybernate(implementation of JPA)

@RestController
public class ItemController {

    // when writing test for this controller
    // test should not depend on the business layer
    @Autowired
    private ItemBusinessService businessService;

    @GetMapping("/dummy-item")
    public Item dummyItem() {
        return new Item(1, "ball", 10, 100);
    }

    @GetMapping("/item-from-business-service")
    public Item itemFromBusinessService() {
        Item item = businessService.retrieveHardcodedItem();
        // some business logic on the item to test!
        // the "unit" being tested is only the item controller
        // this is the logic that we want to test, the controller logic
        // the businessService is something we want to mock out
        return item;
    }

    @GetMapping("/all-items-from-database")
    public List<Item> retrieveAllItems() {
        return  businessService.retrieveAllItems();
    }
}
