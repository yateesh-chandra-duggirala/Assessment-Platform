package com.capstoneproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

	@Test
	void testMain() {
	    assertDoesNotThrow(() -> BackendApplication.main(new String[] {}));
	}
}
