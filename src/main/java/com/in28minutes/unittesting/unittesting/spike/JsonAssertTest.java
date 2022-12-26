package com.in28minutes.unittesting.unittesting.spike;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {

    String actualResponse = "{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";

    @Test
    public void jsonAssert_StrictTrue_ExactMatchExceptForSpaces() throws JSONException {
        String expectedResponse = "{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
        // when strict=true json structure should match exactly (but spaced in values allowed)
        JSONAssert.assertEquals(expectedResponse, actualResponse, true);
    }

    @Test
    public void jsonAssert_StrictFalse_ExactMatchExceptForSpaces() throws JSONException {
        String expectedResponse = "{\"id\": 1,\"name\":\"Ball\",\"price\":10}";
        // when strict=false some elements can be missing)
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }

    @Test
    public void jsonAssert_WithoutEscapeChars() throws JSONException {
        String expectedResponse = "{id: 1,name:Ball,price:10}";
        // when strict=false some elements can be missing)
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }

    @Test
    public void jsonAssert_SpaceInValue() throws JSONException {
        String expectedResponse = "{id: 1,name:\"Ball 2\",price:10}";
        // only need escape chars when having space in the value
        //JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }
}
