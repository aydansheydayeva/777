package com.example2.demo2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Base64;
import java.util.Base64.Encoder;
import javax.net.ssl.HttpsURLConnection;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class Demo2ApplicationTests {

	@Test
	void GetServerCertificate() throws IOException, CertificateEncodingException {
		URL url = new URL("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos");
		URLConnection con = url.openConnection();		
		HttpsURLConnection scon = (HttpsURLConnection) con;
		scon.connect();
		Certificate[] certs = scon.getServerCertificates();
		Certificate cert = certs[0];

		String str1 = Base64.getEncoder().encodeToString(cert.getEncoded()).toString();
		String str2 = "";

 
		URL urll = getClass().getResource("mockcert.cer");
		File file = new File(urll.getPath());

        try (InputStream inputStream = new FileInputStream(file)) {
 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[65535];
            int numberOfReadBytes;
 
            while ((numberOfReadBytes = inputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, numberOfReadBytes);
            }
 
            byte[] certificateInBytes = byteArrayOutputStream.toByteArray();
 
            Encoder encoder = Base64.getEncoder();
            byte[] encodedBytes = encoder.encode(certificateInBytes);
            String base64EncodedString = new String(encodedBytes);
            str2 = base64EncodedString.toString();
		}

		assertEquals(str1, str2);
	}

	@Test
	void checkJSON() throws IOException, CertificateEncodingException {
		Controller contr = new Controller();
		assertEquals(true, contr.getrepos());
	}

}
