package com.example1.demo1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;


@SpringBootTest
class Demo2ApplicationTests {

	@Test
	void check1() throws IOException, CertificateEncodingException {
		Controller contr = new Controller();
		assertEquals(HttpStatus.OK, contr.getrepos());
	}

	@Test
	void check2() throws IOException, CertificateEncodingException {
		Controller contr = new Controller();
		assertEquals(HttpStatus.OK, contr.getbranches());
	}

	@Test
	void check3() throws IOException, CertificateEncodingException {
		Controller contr = new Controller();
		assertEquals(HttpStatus.OK, contr.getcommits());
	}

}
