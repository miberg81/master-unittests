package com.in28minutes.unittesting.unittesting.controller;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

/*
@SpringBootTest annotation
1.will launch the entire unit testing app
 launches main app class UnitTestingApplication.java as well,
 not only this class. It performs scan of all classes having
 @SpringBootApplication annotation on them to launch this apps.
 When spring boot app launches all components(services, repos launch)
 2.Will launch in-memory db from the source data.sql (in both main/test folders)
 */

/* H2
to avoid dependency on real db (tests would fail if data become broken)
 spring provide in memory db for testing
 */

/*
application.propeties config file:
1.Config in source->test->resources overwrites the one in
src->main.resources. can be different for test and for prod
if exists in test -> will be used for tests, if is empty ->
the one from main will be taken for both test and prod
2.We can create a specific config for specific test by
by marking thos test with annotation
@TestPropertySource(locations={"classpath:test-configuration.properties"})
This is the highest priority! (overrides others)
 */

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations={"classpath:test-configuration.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ItemControllerIntegrationTest {

    // knows to fire requests on current random port
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void contextLoads() throws JSONException {

        // This service is used to fire requests to the whole spring app
        // with all the controllers up and running
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject
                ("http://localhost:8080/all-items-from-database", String.class);


        // Without strict some elements may be missing in array
        JSONAssert.assertEquals("[{id:10001},{id:10002},{id:10003},{id:10004}," +
                "{id:10005},{id:10006},{id:10007},{id:10008}]", response, false);
    }
}
