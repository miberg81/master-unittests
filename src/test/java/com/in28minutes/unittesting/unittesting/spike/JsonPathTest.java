package com.in28minutes.unittesting.unittesting.spike;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonPathTest {

    @Test
    public void learning() {
        String responseFromService = "[" +
                "  {\"id\": 10000, \"name\": \"Pencil\", \"quantity\": 5}," +
                "  {\"id\": 10001, \"name\": \"Pen\", \"quantity\": 15}," +
                "  {\"id\": 10002, \"name\": \"Eraser\", \"quantity\": 10}" +
                "]";

        DocumentContext context = JsonPath.parse(responseFromService);
        // $ represents the root level of json (the array level)

        // get length of array
        final int length = context.read("$.length()");
        assertThat(length).isEqualTo(3);

        // read all the ids which are present under the root
        final List<Integer> ids = context.read("$..id");
        System.out.println(ids.toString()); // [10000,10001,10002]
        assertThat(ids).containsExactly(10000, 10001, 10002);

        // get the object in position 1 in array
        System.out.println(context.read("$.[1]").toString());
        // {id=10001, name=Pen, quantity=15}

        // print out 2 objects from index  0 to 2 (exclusive)
        System.out.println(context.read("$.[0:2]").toString());
        //[{"id":10000,"name":"Pencil","quantity":5},{"id":10001,"name":"Pen","quantity":15}]

        // Find onbject which hase property name='Eraser'
        System.out.println(context.read("$.[?(@.name=='Eraser')]").toString());
        //[{"id":10002,"name":"Eraser","quantity":10}]

        // find what object has property quantity of 5
        System.out.println(context.read("$.[?(@.quantity==5)]").toString());
        //[{"id":10000,"name":"Pencil","quantity":5}]

    }
}
