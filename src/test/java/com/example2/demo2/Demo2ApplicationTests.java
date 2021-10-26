package com.example2.demo2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.CertificateEncodingException;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo2ApplicationTests {

	@Test
	void check1() throws IOException, CertificateEncodingException {
		String url = "https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos";
		URL https_url = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) https_url.openConnection();
        con.connect();
        java.security.cert.Certificate[] certs = con.getServerCertificates();
        java.security.cert.Certificate cert = certs[0];

		String crt1 = Base64.getEncoder().encodeToString(cert.getEncoded());
		String crt2 = "";


		URL urll = getClass().getResource("crt.cer");
		File file = new File(urll.getPath());

 
        try (InputStream inputStream = new FileInputStream(file)) {
 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[65535];
            int numberOfReadBytes;
 
            while ((numberOfReadBytes = inputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, numberOfReadBytes);
            }
 
            byte[] certificateInBytes = byteArrayOutputStream.toByteArray();
 
            java.util.Base64.Encoder encoder = Base64.getEncoder();
            byte[] encodedBytes = encoder.encode(certificateInBytes);
            String base64EncodedString = new String(encodedBytes);
			crt2 = base64EncodedString;
        }
		assertEquals(crt1, crt2);
	}

	@Test
	void check2() throws IOException, CertificateEncodingException {
		Controller contr = new Controller();
		assertEquals(true, contr.getrepos());
	}
}
