package com.in28minutes.unittesting.unittesting;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
// we can use a custom config file for a particular test (this will have the highest priority)
@TestPropertySource(locations = {"classpath:test-configuration.properties"})
class UnitTestingApplicationTests {

	@Test
	void contextLoads() {
	}
}
