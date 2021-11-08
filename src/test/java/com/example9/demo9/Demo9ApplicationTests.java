package com.example9.demo9;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo9ApplicationTests {

	@Test
	void check1() {
		controller contr = new controller();
		assertEquals(true, contr.endToend());
	}

}
