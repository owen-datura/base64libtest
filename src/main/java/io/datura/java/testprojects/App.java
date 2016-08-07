package io.datura.java.testprojects;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;

public class App {
	public static void main(String[] args) {
		System.out.println("## Start");
		final String testUrl = getTestUrl();
		System.out.println("# Test URL:\t\t\t" + testUrl);

		System.out.println("\t\t## ENCODE ##\n");

		String oe = oreillyEncode(testUrl);
		System.out.println("# O'Reilly Encoder:\n" + oe);
		String aue = apacheUnsafeEncode(testUrl);
		System.out.println("# Apache *Un*Safe Encoder:\t" + aue);
		String ase = apacheSafeEncode(testUrl);
		System.out.println("# Apache Safe Encoder:\t\t" + ase);

		System.out.println("\n\t\t## DECODE ##");
		{
			// oreilly encoded / oreilly decoded
			String oeod = oreillyDecode(oe);
			System.out.println("# O'Reilly Encoded / O'Reilly Decoded:\t\t" + oeod);
		}

		{
			// oreilly encoded / apache decoded
			String oead = apacheDecode(oe);
			System.out.println("# O'Reilly Encoded / Apache Decoded:\t\t" + oead);
		}

		{
			// apache unsafe / oreilly decoded
			String auod = oreillyDecode(aue);
			System.out.println("# Apache (Unsafe) Encoded / O'Reilly Decoded:\t" + auod);
		}

		{
			// apache unsafe / apache decoded
			String auad = apacheDecode(aue);
			System.out.println("# Apache (Unsafe) Encoded / Apache Decoded:\t" + auad);
		}

		{
			// apache safe / oreilly decoded
			String asod = oreillyDecode(ase);
			System.out.println("# Apache (Safe) Encoded / O'Reilly Decoded:\t" + asod);
		}

		{
			// apache safe / apache decoded
			String asad = apacheDecode(ase);
			System.out.println("# Apache (Safe) Encoded / Apache Decoded:\t" + asad);
		}
	}

	private static String oreillyDecode(final String toDecode) {
		return Base64Decoder.decode(toDecode);
	}

	private static String apacheDecode(final String toDecode) {
		byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(toDecode);
		return new String(decoded, getDefaultCharset());
	}

	private static String apacheUnsafeEncode(final String testUrl) {
		return org.apache.commons.codec.binary.Base64.encodeBase64String(testUrl.getBytes(getDefaultCharset()));
	}

	private static String apacheSafeEncode(final String toEncode) {
		return org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(toEncode.getBytes(getDefaultCharset()));
	}

	private static String oreillyEncode(final String toEncode) {
		return Base64Encoder.encode(toEncode);
	}

	private static String getTestUrl() {
		final StringBuilder b = new StringBuilder();
		b.append("http:\\\\testgoogledomain.example.org\\");
		b.append("WebServlet");
		b.append("?webAction");
		b.append("=");
		b.append("convertFileType");
		b.append("&");
		b.append("recalculateSplines");
		b.append("=");
		b.append("true");
		b.append("&");
		b.append("itemCode");
		b.append("=");
		b.append("Untitled United Worker's Bomber Jacket - Small With Extra Pockets %20% Wool?!");
		b.append("&");
		b.append("ghostBusterActor");
		b.append("=");
		b.append("&& BILL MURRAY &&");
		return b.toString();
	}

	private static Charset getDefaultCharset() {
		return StandardCharsets.UTF_8;
	}
}
