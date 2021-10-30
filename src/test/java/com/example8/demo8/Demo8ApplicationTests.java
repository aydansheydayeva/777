
package com.example8.demo8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;


@SpringBootTest
class Demo8ApplicationTests {

	@Test
	void check1() throws IOException, CertificateEncodingException {
		Controller contr = new Controller();
		assertEquals(true, contr.getrepos());
	}
}