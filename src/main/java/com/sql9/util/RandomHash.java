package com.sql9.util;

public class RandomHash {

	private static FastRandom random = new FastRandom();

	public static byte[] encode(String plainText) {
		int seed = random.nextInt();
		FastRandom random = new FastRandom(seed);
		byte[] utf8 = UTF8.fromString(plainText);
		int n = utf8.length;
		int pad = 0;
		while (((n + 1) + pad) % 4 != 0) {
			pad++;
		}
		byte[] data = new byte[((n + 6) + pad)];
		data[0] = (byte) 69;
		BigEndian.setInt(data, 1, seed);
		data[5] = (byte) (random.nextInt() + pad);
		for (int i = 0; i < n + pad; i++) {
			if (i < n) {
				data[i + 6] = (byte) (utf8[i] + random.nextInt());
			} else {
				data[i + 6] = (byte) random.nextInt();
			}
		}
		return data;
	}

	public static String decode(byte[] data) {
		int n = data.length - 6;
		if (n < 0) {
			throw new IllegalArgumentException("data.length = " + data.length);
		}
		FastRandom random = new FastRandom(BigEndian.getInt(data, 1));
		int pad = ((data[5] - random.nextInt()) + 256) & 255;
		if (pad < 0 || pad > 3) {
			throw new IllegalArgumentException("pad = " + pad);
		}
		n -= pad;
		byte[] utf8 = new byte[n];
		for (int i = 0; i < n; i++) {
			utf8[i] = (byte) (data[i + 6] - random.nextInt());
		}
		return UTF8.toString(utf8);
	}
}
