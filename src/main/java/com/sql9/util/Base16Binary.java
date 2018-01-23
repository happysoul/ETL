package com.sql9.util;

/* compiled from: Unknown Source */
public abstract class Base16Binary {
    public static byte[] fromString(String string) {
        return fromString(string, 0, string.length());
    }

    public static byte[] fromString(String string, int offset, int length) {
        byte[] bytes = new byte[(length / 2)];
        int j = 0;
        for (int k = 0; k < length; k += 2) {
            int hi = Character.digit(string.charAt(offset + k), 16);
            int lo = Character.digit(string.charAt((offset + k) + 1), 16);
            if (hi == -1 || lo == -1) {
                throw new IllegalArgumentException(string);
            }
            bytes[j] = (byte) ((hi * 16) + lo);
            j++;
        }
        return bytes;
    }

    public static String toString(byte[] bytes) {
        return toString(bytes, 0, bytes.length);
    }

    public static String toString(byte[] bytes, int offset, int length) {
        char[] chars = new char[(length * 2)];
        int j = 0;
        int k = 0;
        while (j < length) {
            int value = (bytes[offset + j] + 256) & 255;
            chars[k] = Character.forDigit(value >> 4, 16);
            chars[k + 1] = Character.forDigit(value & 15, 16);
            j++;
            k += 2;
        }
        return new String(chars);
    }

    public static String dump(byte[] bytes) {
        return dump(bytes, 0, bytes.length);
    }

    public static String dump(byte[] bytes, int offset, int length) {
        String str = toString(bytes, offset, length);
        StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            char c = (char) bytes[offset + i];
            if (c % 128 < 32) {
                c = '.';
            }
            sb.append(c);
        }
        return str + " [" + sb + "]";
    }
}
