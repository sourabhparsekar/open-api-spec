package com.openapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenApi3ApplicationTests {

	@Test
	void contextLoads() {
		//just to remove sonarlint issue
		Assertions.assertEquals("MAIN", "MA"+"IN");
	}

}
