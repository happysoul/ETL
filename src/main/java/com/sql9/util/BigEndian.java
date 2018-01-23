package com.sql9.util;

/* compiled from: Unknown Source */
public abstract class BigEndian {
    public static byte[] getShortBytes(short value) {
        byte[] bytes = new byte[2];
        setShort(bytes, value);
        return bytes;
    }

    public static byte[] getIntBytes(int value) {
        byte[] bytes = new byte[4];
        setInt(bytes, value);
        return bytes;
    }

    public static byte[] getLongBytes(long value) {
        byte[] bytes = new byte[8];
        setLong(bytes, value);
        return bytes;
    }

    public static byte[] get48BitLongBytes(long value) {
        byte[] bytes = new byte[6];
        set48BitLong(bytes, value);
        return bytes;
    }

    public static short getShort(byte[] bytes) {
        return getShort(bytes, 0);
    }

    public static short getShort(byte[] bytes, int offset) {
        return (short) (((bytes[offset] << 8) & 65280) | (bytes[offset + 1] & 255));
    }

    public static int getInt(byte[] bytes) {
        return getInt(bytes, 0);
    }

    public static int getInt(byte[] bytes, int offset) {
        return ((((bytes[offset] << 24) & -16777216) | ((bytes[offset + 1] << 16) & 16711680)) | ((bytes[offset + 2] << 8) & 65280)) | (bytes[offset + 3] & 255);
    }

    public static long getLong(byte[] bytes) {
        return getLong(bytes, 0);
    }

    public static long getLong(byte[] bytes, int offset) {
        return ((((long) getInt(bytes, offset)) & 4294967295L) << 32) | (((long) getInt(bytes, offset + 4)) & 4294967295L);
    }

    public static long get48BitLong(byte[] bytes) {
        return get48BitLong(bytes, 0);
    }

    public static long get48BitLong(byte[] bytes, int offset) {
        return ((((long) getShort(bytes, offset)) & 65535) << 32) | (((long) getInt(bytes, offset + 2)) & 4294967295L);
    }

    public static void setShort(byte[] bytes, short value) {
        setShort(bytes, 0, value);
    }

    public static void setShort(byte[] bytes, int offset, short value) {
        bytes[offset] = (byte) ((value >>> 8) & 255);
        bytes[offset + 1] = (byte) (value & 255);
    }

    public static void setInt(byte[] bytes, int value) {
        setInt(bytes, 0, value);
    }

    public static void setInt(byte[] bytes, int offset, int value) {
        bytes[offset] = (byte) ((value >>> 24) & 255);
        bytes[offset + 1] = (byte) ((value >>> 16) & 255);
        bytes[offset + 2] = (byte) ((value >>> 8) & 255);
        bytes[offset + 3] = (byte) (value & 255);
    }

    public static void setLong(byte[] bytes, long value) {
        setLong(bytes, 0, value);
    }

    public static void setLong(byte[] bytes, int offset, long value) {
        int lo = (int) value;
        setInt(bytes, offset, (int) (value >>> 32));
        setInt(bytes, offset + 4, lo);
    }

    public static void set48BitLong(byte[] bytes, long value) {
        set48BitLong(bytes, 0, value);
    }

    public static void set48BitLong(byte[] bytes, int offset, long value) {
        int lo = (int) value;
        setShort(bytes, offset, (short) ((int) (value >>> 32)));
        setInt(bytes, offset + 2, lo);
    }
}
