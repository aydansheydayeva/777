package com.example2.demo2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo2ApplicationTests {

	@Test
	void check1() throws IOException , CertificateException{
		String url = "https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos";
		URL https_url = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) https_url.openConnection();
        con.connect();
        java.security.cert.Certificate[] certs = con.getServerCertificates();
        java.security.cert.Certificate cert = certs[0];

		String crt1 = Base64.getEncoder().encodeToString(cert.getEncoded());
		String crt2 = "";

        CertificateFactory fac = CertificateFactory.getInstance("X509");
        FileInputStream is = new FileInputStream("crt.cer");
        X509Certificate certif = (X509Certificate) fac.generateCertificate(is);
        crt2 = Base64.getEncoder().encodeToString(certif.getEncoded());

		assertEquals(crt1, crt2);
	}

	@Test
	void check2() throws IOException, CertificateEncodingException {
		Controller contr = new Controller();
		assertEquals(true, contr.getrepos());
	}
}
