package com.rbc.bbp0;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class ApiApplicationTests {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> ApiApplication.main(new String[]{}));

	}

}
